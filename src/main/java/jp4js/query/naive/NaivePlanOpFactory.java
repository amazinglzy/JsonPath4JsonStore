package jp4js.query.naive;

import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.query.PlanOperator;
import jp4js.query.RecordSet;
import jp4js.query.RecordSet.Record;
import jp4js.utils.Configuration;
import jp4js.utils.spi.json.JsonProvider;
import jp4js.utils.filter.Filter;
import jp4js.utils.iter.Iter;

import java.util.List;

public class NaivePlanOpFactory {

    public static PropertyPlanOperator createPropertyPlanOperator(PlanOperator<Record> planOp, List<String> properties, Configuration configuration) {
        return new PropertyPlanOperator(planOp, properties, configuration);
    }

    public static ArraySelectionsPlanOperator createArraySelectionsPlanOperator(PlanOperator<Record> planOp, ArraySelections selections, Configuration configuration) {
        return new ArraySelectionsPlanOperator(planOp, selections, configuration);
    }

    public static WildcardPlanOperator createWildcardPlanOperator(PlanOperator<Record> planOp, Configuration configuration) {
        return new WildcardPlanOperator(planOp, configuration);
    }

    public static ScanPlanOperator createScanPlanOperator(PlanOperator<Record> planOp, List<String> properties, Configuration configuration) {
        return new ScanPlanOperator(planOp, properties, configuration);
    }

    public static FilterPlanOperator createFilterPlanOperator(PlanOperator<Record> planOp, Filter<Record> filter, Configuration configuration ){
        return new FilterPlanOperator(planOp, filter, configuration);
    }

    public static class PropertyPlanOperator implements PlanOperator<Record> {
        private List<String> properties;
        private PlanOperator<Record> planOp;
        private Configuration configuration;

        public PropertyPlanOperator(PlanOperator<Record> planOp, List<String> properties, Configuration configuration) {
            this.planOp = planOp;
            this.properties = properties;
            this.configuration = configuration;
        }

        @Override
        public Iter<Record> iterator() {
            Iter<Record> parentIterator = this.planOp.iterator();
            RecordSet newData = new RecordSet(configuration);
            while (parentIterator.hasNext()) {
                transformByProperties(parentIterator.read(), properties, newData, configuration);
                parentIterator.next();
            }
            return newData.iterator();
        }

        private void transformByProperties(Record record, List<String> properties, RecordSet newData, Configuration configuration) {
            for (String property: properties) {
                Object val = configuration.jsonProvider().getMapValue(record.getValue(), property);
                if (val != JsonProvider.UNDEFINED) newData.append(newData.new Record(record.getPath() + "." + property, val));
            }
        }
    }


    public static class ArraySelectionsPlanOperator implements PlanOperator<Record> {
        private ArraySelections selections;
        private PlanOperator<Record> planOp;
        private Configuration configuration;

        public ArraySelectionsPlanOperator(PlanOperator<Record> planOp, ArraySelections selections, Configuration configuration) {
            this.planOp = planOp;
            this.selections = selections;
            this.configuration = configuration;
        }

        @Override
        public Iter<Record> iterator() {
            Iter<Record> parentIterator = this.planOp.iterator();
            RecordSet newData = new RecordSet(configuration);
            while (parentIterator.hasNext()) {
                transformByIndicies(parentIterator.read(), selections.select(), newData, configuration);
                parentIterator.next();
            }
            return newData.iterator();
        }

        private void transformByIndicies(Record record, List<Integer> indices, RecordSet newData, Configuration configuration) {
            for (Integer index: indices) {
                Object val = configuration.jsonProvider().getArrayIndex(record.getValue(), index);
                if (val != JsonProvider.UNDEFINED) { // getMapValue will return reference of UNDEFINED
                    newData.append(record.getPath() + "[" + String.valueOf(index) + "]", val);
                }
            }
        }
    }


    public static class WildcardPlanOperator implements PlanOperator<Record> {
        private PlanOperator<Record> planOp;
        private Configuration configuration;

        public WildcardPlanOperator(PlanOperator<Record> planOp, Configuration configuration) {
            this.planOp = planOp;
            this.configuration = configuration;
        }

        @Override
        public Iter<Record> iterator() {
            Iter<Record> parentIterator = this.planOp.iterator();
            RecordSet newData = new RecordSet(configuration);
            while (parentIterator.hasNext()) {
                transformByWildcard(parentIterator.read(), newData, configuration);
                parentIterator.next();
            }
            return newData.iterator();
        }

        private void transformByWildcard(Record record, RecordSet newData, Configuration configuration) {
            if (configuration.jsonProvider().isArray(record.getValue())) {
                for (int idx = 0; idx < configuration.jsonProvider().length(record.getValue()); idx ++) {
                    newData.append(newData.new Record(
                        record.getPath() + "[" + String.valueOf(idx) + "]", 
                        configuration.jsonProvider().getArrayIndex(record.getValue(), idx)));
                }
            } else if (configuration.jsonProvider().isMap(record.getValue())) {
                for (String property: configuration.jsonProvider().getPropertyKeys(record.getValue())) {
                    newData.append(
                        record.getPath() + "." + property, 
                        configuration.jsonProvider().getMapValue(record.getValue(), property));
                }
            }
        }
    }

    public static class ScanPlanOperator implements PlanOperator<Record> {
        private List<String> properties;
        private PlanOperator<Record> planOp;
        private Configuration configuration;

        public ScanPlanOperator(PlanOperator<Record> planOp, List<String> properties, Configuration configuration) {
            this.planOp = planOp;
            this.configuration = configuration;
            this.properties = properties;
        }

        @Override
        public Iter<Record> iterator() {
            Iter<Record> parentIterator = this.planOp.iterator();
            RecordSet newData = new RecordSet(configuration);
            while (parentIterator.hasNext()) {
                transformByScan(parentIterator.read(), properties, newData, configuration);
                parentIterator.next();
            }
            return newData.iterator();
        }

        private void iterateJsonAndSelectProperties(String path, Object json, List<String> properties, RecordSet newData, Configuration configuration) {
            if (configuration.jsonProvider().isArray(json)) {
                for (int idx = 0; idx < configuration.jsonProvider().length(json); idx++) {
                    iterateJsonAndSelectProperties(path + "[" + String.valueOf(idx) + "]", 
                                configuration.jsonProvider().getArrayIndex(json, idx), 
                                properties,
                                newData,
                                configuration);
                }
            } else if (configuration.jsonProvider().isMap(json)) {
                for (String property: properties) {
                    Object obj = configuration.jsonProvider().getMapValue(json, property);
                    if (obj != JsonProvider.UNDEFINED) {
                        newData.append(path + "." + property, obj);
                    }
                }

                for (String property: configuration.jsonProvider().getPropertyKeys(json)) {
                    iterateJsonAndSelectProperties(path + "." + property, 
                                configuration.jsonProvider().getMapValue(json, property), 
                                properties,
                                newData,
                                configuration);
                }
            }
        }

        private void transformByScan(Record record, List<String> properties, RecordSet newData, Configuration configuration) {
            iterateJsonAndSelectProperties(record.getPath(), record.getValue(), properties, newData, configuration);
        }
    }

    public static class FilterPlanOperator implements PlanOperator<Record> {
        private Filter<Record> filter;
        private PlanOperator<Record> planOp;
        private Configuration configuration;

        public FilterPlanOperator(PlanOperator<Record> planOp, Filter<Record> filter, Configuration configuration ){
            this.filter = filter;
            this.planOp = planOp;
            this.configuration = configuration;
        }

        @Override
        public Iter<Record> iterator() {
            RecordSet newData = new RecordSet(configuration);
            Iter<Record> iterator = this.planOp.iterator();
            while (iterator.hasNext()) {
                Record r = iterator.read(); iterator.next();
                if (filter.accept(r)) newData.append(r);
            }
            return newData.iterator();
        }
    }

}

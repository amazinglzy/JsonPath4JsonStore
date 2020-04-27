package jp4js.query.scan;

import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.query.RecordSet;
import jp4js.query.RecordSet.Record;
import jp4js.utils.Configuration;
import jp4js.utils.spi.json.JsonProvider;
import jp4js.utils.filter.Filter;

import java.util.List;
import java.util.Iterator;

public class RecordGenerator {

    private RecordSet data;
    private Configuration configuration;

    public RecordGenerator(RecordSet data, Configuration configuration) {
        this.data = data;
        this.configuration = configuration;
    }

    public RecordGenerator step(List<String> properties) {
        RecordSet newData = new RecordSet(configuration);
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            transformByProperties(iterator.next(), properties, newData);
        }
        this.data = newData;
        return this;
    }

    public RecordGenerator step(ArraySelections selections) {
        RecordSet newData = new RecordSet(configuration);
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            transformByIndicies(iterator.next(), selections.select(), newData);
        }
        this.data = newData;
        return this;
    }

    public RecordGenerator stepWildcard() {
        RecordSet newData = new RecordSet(configuration);
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            transformByWildcard(iterator.next(), newData);
        }
        this.data = newData;
        return this;
    }

    public RecordGenerator stepScan(List<String> properties) {
        RecordSet newData = new RecordSet(configuration);
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            transformByScan(iterator.next(), properties, newData);
        }
        this.data = newData;
        return this;
    }

    public RecordGenerator filter(Filter<Record> filter) {
        RecordSet newData = new RecordSet(configuration);
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            Record r = iterator.next();
            if (filter.accept(r)) newData.append(r);
        }
        this.data = newData;
        return this;
    }

    public RecordSet data() {
        return this.data;
    }

    /*
    Helper Functions
    */
    public void transformByProperties(Record record, List<String> properties, RecordSet newData) {
        for (String property: properties) {
            Object val = configuration.jsonProvider().getMapValue(record.getValue(), property);
            if (val != JsonProvider.UNDEFINED) newData.append(newData.new Record(record.getPath() + "." + property, val));
        }
    }

    public void transformByIndicies(Record record, List<Integer> indices, RecordSet newData) {
        for (Integer index: indices) {
            Object val = configuration.jsonProvider().getArrayIndex(record.getValue(), index);
            if (val != JsonProvider.UNDEFINED) { // getMapValue will return reference of UNDEFINED
                newData.append(record.getPath() + "[" + String.valueOf(index) + "]", val);
            }
        }
    }

    public void transformByWildcard(Record record, RecordSet newData) {
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

    private void iterateJsonAndSelectProperties(String path, Object json, List<String> properties, RecordSet newData) {
        if (configuration.jsonProvider().isArray(json)) {
            for (int idx = 0; idx < configuration.jsonProvider().length(json); idx++) {
                iterateJsonAndSelectProperties(path + "[" + String.valueOf(idx) + "]", 
                            configuration.jsonProvider().getArrayIndex(json, idx), 
                            properties,
                            newData);
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
                            newData);
            }
        }
    }

    private void transformByScan(Record record, List<String> properties, RecordSet newData) {
        iterateJsonAndSelectProperties(record.getPath(), record.getValue(), properties, newData);
    }
}

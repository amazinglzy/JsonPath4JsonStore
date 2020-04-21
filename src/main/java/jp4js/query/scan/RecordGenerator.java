package jp4js.query.scan;

import jp4js.index.node.ArrayNode.ArraySelections;
import jp4js.query.RecordSet;
import jp4js.query.RecordSet.Record;
import jp4js.utils.Configuration;
import jp4js.utils.spi.json.JsonProvider;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

public class RecordGenerator {
    private RecordSet data;
    private Configuration configuration;

    public RecordGenerator(RecordSet data, Configuration configuration) {
        this.data = data;
        this.configuration = configuration;
    }

    public RecordGenerator step(List<String> properties) {
        List<Record> records = new LinkedList<>();
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            records.addAll(transformByProperties(iterator.next(), properties));
        }
        this.data = new RecordSet(configuration, records);
        return this;
    }

    public RecordGenerator step(ArraySelections selections) {
        List<Record> records = new LinkedList<>();
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            records.addAll(transformByIndicies(iterator.next(), selections.select()));
        }
        this.data = new RecordSet(configuration, records);
        return this;
    }

    public RecordGenerator stepWildcard() {
        List<Record> records = new LinkedList<>();
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            records.addAll(transformByWildcard(iterator.next()));
        }
        this.data = new RecordSet(configuration, records);
        return this;
    }

    public RecordGenerator stepScan(List<String> properties) {
        List<Record> records = new LinkedList<>();
        Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            records.addAll(transformByScan(iterator.next(), properties));
        }
        this.data = new RecordSet(configuration, records);
        return this;
    }

    public RecordSet data() {
        return this.data;
    }

    /*
    Helper Functions
    */
    public List<Record> transformByProperties(Record record, List<String> properties) {
        return new LinkedList<>(){{
            for (String property: properties) {
                Object val = configuration.jsonProvider().getMapValue(record.getValue(), property);
                if (val != JsonProvider.UNDEFINED) add(new Record(record.getPath() + "." + property, val, configuration));
            }
        }};
    }

    public List<Record> transformByIndicies(Record record, List<Integer> indices) {
        return new LinkedList<>() {{
            for (Integer index: indices) {
                Object val = configuration.jsonProvider().getArrayIndex(record.getValue(), index);
                if (val != JsonProvider.UNDEFINED) { // getMapValue will return reference of UNDEFINED
                    add(new Record(record.getPath() + "[" + String.valueOf(index) + "]", val, configuration));
                }
            }
        }};
    }

    public List<Record> transformByWildcard(Record record) {
        return new LinkedList<>() {{
            if (configuration.jsonProvider().isArray(record.getValue())) {
                for (int idx = 0; idx < configuration.jsonProvider().length(record.getValue()); idx ++) {
                    add(new Record(record.getPath() + "[" + String.valueOf(idx) + "]", 
                                   configuration.jsonProvider().getArrayIndex(record.getValue(), idx),
                                   configuration));
                }
            } else if (configuration.jsonProvider().isMap(record.getValue())) {
                for (String property: configuration.jsonProvider().getPropertyKeys(record.getValue())) {
                    add(new Record(record.getPath() + "." + property, 
                                   configuration.jsonProvider().getMapValue(record.getValue(), property),
                                   configuration)); 
                }
            }
        }};
    }

    private void iterateJsonAndSelectProperties(String path, Object json, List<Record> visitSet, List<String> properties) {
        if (configuration.jsonProvider().isArray(json)) {
            for (int idx = 0; idx < configuration.jsonProvider().length(json); idx++) {
                iterateJsonAndSelectProperties(path + "[" + String.valueOf(idx) + "]", 
                            configuration.jsonProvider().getArrayIndex(json, idx), 
                            visitSet, 
                            properties);
            }
        } else if (configuration.jsonProvider().isMap(json)) {
            for (String property: properties) {
                Object obj = configuration.jsonProvider().getMapValue(json, property);
                if (obj != JsonProvider.UNDEFINED) {
                    visitSet.add(new Record(path + "." + property, obj, configuration));
                }
            }

            for (String property: configuration.jsonProvider().getPropertyKeys(json)) {
                iterateJsonAndSelectProperties(path + "." + property, 
                            configuration.jsonProvider().getMapValue(json, property), 
                            visitSet, 
                            properties);
            }
        }
    }

    private List<Record> transformByScan(Record record, List<String> properties) {
        List<Record> ret = new LinkedList<>();
        iterateJsonAndSelectProperties(record.getPath(), record.getValue(), ret, properties);
        return ret;
    }


}

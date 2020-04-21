package jp4js.query;

import java.util.List;

import jp4js.utils.Configuration;

import java.util.Iterator;
import java.util.LinkedList;

public class RecordSet {
    public static class Record {
        private String path;
        private Object value;
        private Configuration configuration;

        public Record(String path, Object value, Configuration configuration) {
            this.path = path;
            this.value = value;
            this.configuration = configuration;
        }

        public String getPath() {
            return this.path;
        }

        public Object getValue() {
            return this.configuration.jsonProvider().unwrap(this.value);
        }
    }

    private List<Record> data;
    private Configuration configuration;

    public RecordSet(Configuration configuration) {
        this.data = new LinkedList<>();
        this.configuration = configuration;
    }

    public RecordSet(Configuration configuration, List<Record> data) {
        this.data = new LinkedList<>();
        this.configuration = configuration;
        this.data.addAll(data);
    }

    public void append(Record record) {
        this.data.add(record);
    }

    public Iterator<Record> iterator() {
        return this.data.iterator();
    }
}
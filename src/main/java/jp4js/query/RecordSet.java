package jp4js.query;

import java.util.List;

import jp4js.utils.Configuration;
import jp4js.utils.Value;

import java.util.Iterator;
import java.util.LinkedList;

public class RecordSet {
    public class Record implements Value {
        private String path;
        private Object value;

        public Record(String path, Object value) {
            this.path = path;
            this.value = value;
        }

        public String getPath() {
            return this.path;
        }

        public Object getValue() {
            return configuration.jsonProvider().unwrap(this.value);
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

    public void append(String path, Object value) {
        this.data.add(new Record(path, value));
    }

    public Iterator<Record> iterator() {
        return this.data.iterator();
    }
}
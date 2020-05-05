package jp4js.query;

import java.util.List;

import jp4js.utils.Configuration;
import jp4js.utils.Value;
import jp4js.utils.iter.Iter;
import jp4js.query.RecordSet.Record;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class RecordSet implements PlanOperator<Record> {
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
        this.data = new ArrayList<>();
        this.configuration = configuration;
        this.data.addAll(data);
    }

    public void append(Record record) {
        this.data.add(record);
    }

    public void append(String path, Object value) {
        this.data.add(new Record(path, value));
    }

    public Iterator<Record> iter() {
        return this.data.iterator();
    }

    public Iter<Record> iterator() {
        return new RecordIterator();
    }

    public class RecordIterator implements Iter<Record> {
        private int idx;
        public RecordIterator() {
            this.idx = 0;
        }
        private RecordIterator(int idx) {
            this.idx = idx;
        }

        public Record read() {
            return data.get(this.idx);
        }

        public void next() {
            this.idx ++;
        }

        public boolean hasNext() {
            return this.idx < data.size();
        }

        public Iter<Record> cloneCurrentIterator() {
            return new RecordIterator(this.idx);
        }
    }
}
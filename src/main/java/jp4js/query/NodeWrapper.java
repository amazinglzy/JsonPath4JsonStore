package jp4js.query;

import jp4js.index.node.Node;
import jp4js.index.node.NodeIterator;
import jp4js.query.RecordSet.Record;

import java.util.Iterator;

public class NodeWrapper implements Iterator<Record> {
    private NodeIterator iter;
    private RecordSet recordSet;

    public NodeWrapper(NodeIterator iter, RecordSet recordSet) {
        this.iter = iter;
        this.recordSet = recordSet;
    }

    @Override
    public boolean hasNext() {
        return this.iter.hasNext();
    }

    @Override
    public Record next() {
        Node node = this.iter.read();
        this.iter.next();
        Record record = this.recordSet.new Record("", node.getValue());
        this.recordSet.append(record);
        return record;
    }
}
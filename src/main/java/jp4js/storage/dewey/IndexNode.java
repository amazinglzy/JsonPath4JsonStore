package jp4js.storage.dewey;

import java.util.LinkedList;

import jp4js.nf2.DType;

public class IndexNode {
    public LinkedList<Integer> indexes;
    public DType.Instance value;

    public IndexNode(LinkedList<Integer> indexes, DType.Instance value) {
        this.indexes = indexes;
        this.value = value;
    }
}

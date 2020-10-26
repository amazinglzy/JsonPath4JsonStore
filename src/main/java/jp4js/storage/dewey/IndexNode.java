package jp4js.storage.dewey;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp4js.algebra.Domain;
import jp4js.algebra.tpl.AtomicValue;
import jp4js.algebra.tpl.NestedData;

public class IndexNode {
    public List<Integer> indexes;
    public NestedData data;

    public IndexNode(List<Integer> indexes, Domain.Instance value) {
        this.indexes = indexes;
        this.data = new AtomicValue(value.type(), value);
    }

    public IndexNode(List<Integer> indexes, NestedData data) {
        this.indexes = indexes;
        this.data = data;
    }

    @Override
    public String toString() {
        return this.indexes.toString() + ":" + this.data.toString();
    }

    public static Comparator<IndexNode> comparator(int level) {
        return new Comparator<IndexNode>() {
            @Override
            public int compare(IndexNode n1, IndexNode n2) {
                Iterator<Integer> iter1 = n1.indexes.iterator();
                Iterator<Integer> iter2 = n2.indexes.iterator();
                for (int i = 0; i < level; i++) {
                    if (iter1.hasNext() && iter2.hasNext()) {
                        Integer a = iter1.next();
                        Integer b = iter2.next();
                        if (a != b) {
                            return a - b;
                        }
                    } else if (iter1.hasNext()) {
                        return 1;
                    } else if (iter2.hasNext()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                return 0;
            }
        };
    }
}

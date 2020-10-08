package jp4js.storage.dewey;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import jp4js.algebra.DType;
import jp4js.utils.Utils;

public class IndexNode {
    public LinkedList<Integer> indexes;
    public DType.Instance value;

    public IndexNode(LinkedList<Integer> indexes, DType.Instance value) {
        this.indexes = indexes;
        this.value = value;
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

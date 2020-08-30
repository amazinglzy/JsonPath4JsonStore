package jp4js.storage.node;

import java.util.Comparator;

import jp4js.nf2.DType;

public class LabelNode implements Comparable<LabelNode> {
    public DType.Instance value;
    public long first_visit, last_visit;
    public int level;

    public LabelNode(long firstVisit, long lastVisit, int level, DType.Instance value) {
        this.value = value;
        this.first_visit = firstVisit;
        this.last_visit = lastVisit;
        this.level = level;
    }

    @Override
    public int compareTo(LabelNode o) {
        if (this.first_visit != o.first_visit) {
            return this.first_visit < o.first_visit ? -1: 1;
        }
        return 0;
    }

    public static Comparator<LabelNode> comparator() {
        return new Comparator<LabelNode>() {
            @Override
            public int compare(LabelNode o1, LabelNode n2) {
                return o1.compareTo(n2);
            }
        };
    }
}

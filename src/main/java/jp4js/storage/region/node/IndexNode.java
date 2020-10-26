package jp4js.storage.region.node;

import java.util.Comparator;

import jp4js.algebra.Domain;

public class IndexNode implements Comparable<IndexNode> {
    public String name;
    public Domain.Instance value;
    public long first_visit, last_visit;
    public int level;

    public IndexNode(String name, long firstVisit, long lastVisit, int level, Domain.Instance value) {
        this.name = name;
        this.value = value;
        this.first_visit = firstVisit;
        this.last_visit = lastVisit;
        this.level = level;
    }

    public boolean isRepeatable() {
        return false;
    }

    @Override
    public int compareTo(IndexNode o) {
        if (this.first_visit != o.first_visit) {
            return this.first_visit < o.first_visit ? -1: 1;
        }
        return 0;
    }

    public static Comparator<IndexNode> comparator() {
        return new Comparator<IndexNode>() {
            @Override
            public int compare(IndexNode o1, IndexNode n2) {
                return o1.compareTo(n2);
            }
        };
    }
}

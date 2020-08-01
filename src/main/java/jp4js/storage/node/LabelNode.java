package jp4js.storage.node;

import java.util.Comparator;

import jp4js.nf2.DType;

public class LabelNode implements Comparable<LabelNode> {
    private String path;
    private DType.Instance value;
    private long firstVisit, lastVisit;
    private int level;

    public LabelNode(String path, long firstVisit, long lastVisit, int level, DType.Instance value) {
        this.path = ""; 
        this.value = value;
        this.firstVisit = firstVisit;
        this.lastVisit = lastVisit;
        this.level = level;
    }

    public DType.Instance getValue() {
        return value;
    }

    public long getFirstVisit() {
        return firstVisit;
    }

    public long getLastVisit() {
        return lastVisit;
    }

    public int getLevel() {
        return level;
    }

    public String getPath() {
        return path;
    }

    public void setValue(DType.Instance value) {
        this.value = value;
    }

    public void setFirstVisit(long firstVisit) {
        this.firstVisit = firstVisit;
    }

    public void setLastVisit(long lastVisit) {
        this.lastVisit = lastVisit;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(LabelNode o) {
        if (this.getFirstVisit() != o.getFirstVisit()) {
            return this.getFirstVisit() < o.getFirstVisit() ? -1: 1;
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

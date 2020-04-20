package jp4js.index.node;

public class Node implements Comparable<Node> {
    private Object value;
    private Object rootDocument;
    private long firstVisit, lastVisit;
    private int level;

    public Node(long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        this.value = value;
        this.firstVisit = firstVisit;
        this.lastVisit = lastVisit;
        this.level = level;
        this.rootDocument = rootDocument;
    }

    public Object getValue() {
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

    public Object getRootDocument() {
        return rootDocument;
    }

    public void setValue(Object value) {
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

    public void setRootDocument(Object rootDocument) {
        this.rootDocument = rootDocument;
    }

    @Override
    public int compareTo(Node o) {
        if (this.getFirstVisit() != o.getFirstVisit()) {
            return this.getFirstVisit() < o.getFirstVisit() ? -1: 1;
        }
        return 0;
    }
}

package jp4js.query.merge;

import jp4js.index.node.LabelNode;

public class Item implements Comparable<Item> {
    public LabelNode root;
    public LabelNode data;

    public Item(LabelNode root, LabelNode data) {
        this.root = root;
        this.data = data;
    }

    public boolean isAncestorOf(Item i) {
        return this.root.getFirstVisit() < i.getRoot().getFirstVisit() && 
            i.getRoot().getLastVisit() < this.root.getLastVisit();
    }

    public boolean isPrecedentOf(Item i) {
        return this.root.getLastVisit() < i.getRoot().getFirstVisit();
    }

    public boolean isDecendentOf(Item i) {
        return i.getRoot().getFirstVisit() < this.root.getFirstVisit() &&
            this.root.getLastVisit() < i.getRoot().getFirstVisit();
    }

    public boolean isFollowing(Item i) {
        return i.getRoot().getLastVisit() < this.root.getFirstVisit();
    }

    public LabelNode getRoot() {
        return this.root;
    }

    public LabelNode getData() {
        return this.data;
    }

    @Override
    public int compareTo(Item i) {
        long k1 = this.root.getFirstVisit();
        long k2 = i.getRoot().getFirstVisit();
        if (k1 == k2) return 0;
        return k1 < k2 ? -1 : 1;
    }
}
package jp4js.index.node;

import jp4js.utils.Iter;

public class CombinedNodeIterator implements Iter<Node> {
    private Iter<Node> iter1, iter2;

    public CombinedNodeIterator(Iter<Node> iter1, Iter<Node> iter2) {
        this.iter1 = iter1;
        this.iter2 = iter2;
    }

    @Override
    public Node read() {
        if (this.iter1.hasNext() && this.iter2.hasNext()) {
            Node n1 = this.iter1.read();
            Node n2 = this.iter2.read();
            if (n1.compareTo(n2) < 0) return n1;
            else return n2;
        } else if (this.iter1.hasNext()) {
            return this.iter1.read();
        } else {
            return this.iter2.read();
        }
    }

    @Override
    public void next() {
        if (this.iter1.hasNext() && this.iter2.hasNext()) {
            Node n1 = this.iter1.read();
            Node n2 = this.iter2.read();
            if (n1.compareTo(n2) < 0) this.iter1.next();
            else this.iter2.next();
            return; // make sure the next if condition will not be verified
        } else if (this.iter1.hasNext()) {
            this.iter1.next();
            return;
        } else {
            this.iter2.next();
            return;
        }
    }

    @Override
    public boolean hasNext() {
        return (this.iter1.hasNext() || this.iter2.hasNext());
    }

    @Override
    public Iter<Node> cloneCurrentIterator() {
        return new CombinedNodeIterator(this.iter1.cloneCurrentIterator(), this.iter2.cloneCurrentIterator());
    }
}

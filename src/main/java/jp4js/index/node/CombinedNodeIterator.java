package jp4js.index.node;

import jp4js.utils.Iter;

public class CombinedNodeIterator implements Iter<LabelNode> {
    private Iter<LabelNode> iter1, iter2;

    public CombinedNodeIterator(Iter<LabelNode> iter1, Iter<LabelNode> iter2) {
        this.iter1 = iter1;
        this.iter2 = iter2;
    }

    @Override
    public LabelNode read() {
        if (this.iter1.hasNext() && this.iter2.hasNext()) {
            LabelNode n1 = this.iter1.read();
            LabelNode n2 = this.iter2.read();
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
            LabelNode n1 = this.iter1.read();
            LabelNode n2 = this.iter2.read();
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
    public Iter<LabelNode> cloneCurrentIterator() {
        return new CombinedNodeIterator(this.iter1.cloneCurrentIterator(), this.iter2.cloneCurrentIterator());
    }
}

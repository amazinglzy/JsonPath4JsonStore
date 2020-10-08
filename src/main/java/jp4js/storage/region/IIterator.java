package jp4js.storage.region;

import jp4js.storage.region.node.IndexNode;
import jp4js.storage.region.node.SingularNode;
import jp4js.storage.region.node.RepeatableNode;

public class IIterator implements RegionIterator<IndexNode> {
    private RegionIterator<SingularNode> sIter;
    private RegionIterator<RepeatableNode> rIter;
    public IIterator(RegionIterator<SingularNode> sIter, RegionIterator<RepeatableNode> rIter) {
        this.sIter = sIter;
        this.rIter = rIter;
    }

    @Override
    public IndexNode read() {
        if (this.sIter.valid() && this.rIter.valid()) {
            IndexNode n1 = this.sIter.read();
            IndexNode n2 = this.rIter.read();
            if (n1.first_visit - n2.first_visit < 0) return n1;
            else return n2;
        } else if (this.sIter.valid()) {
            return this.sIter.read();
        } else {
            return this.rIter.read();
        }
    }

    @Override
    public void next() {
        if (this.sIter.valid() && this.rIter.valid()) {
            IndexNode n1 = this.sIter.read();
            IndexNode n2 = this.rIter.read();
            if (n1.first_visit - n2.first_visit < 0) this.sIter.next();
            else this.rIter.next();
        } else if (this.sIter.valid()) {
            this.sIter.next();
        } else {
            this.rIter.next();
        }
    }

    @Override
    public boolean valid() {
        return (this.sIter.valid() || this.rIter.valid());
    }

    @Override
    public void seek(long visit) {
        this.sIter.seek(visit);
        this.rIter.seek(visit);
    }
}
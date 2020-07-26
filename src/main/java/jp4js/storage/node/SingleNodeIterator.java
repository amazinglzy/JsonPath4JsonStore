package jp4js.storage.node;

import jp4js.utils.iter.Iter;
import java.util.ArrayList;
import java.util.List;


public class SingleNodeIterator implements Iter<LabelNode> {
    private ArrayList<LabelNode> data;
    private int idx;

    public SingleNodeIterator(List<LabelNode> data) {
        this.data = new ArrayList<LabelNode>(data);
        this.idx = 0;
    }

    private SingleNodeIterator(ArrayList<LabelNode> data, int idx) {
        this.data = data;
        this.idx = idx;
    }

    @Override
    public LabelNode read() {
        return this.data.get(this.idx);
    }

    @Override
    public void next() {
        if (this.idx >= this.data.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.idx ++;
    }

    @Override
    public boolean hasNext() {
        return this.idx < this.data.size();
    }

    @Override
    public Iter<LabelNode> cloneCurrentIterator() {
        return new SingleNodeIterator(this.data, this.idx);
    }
}

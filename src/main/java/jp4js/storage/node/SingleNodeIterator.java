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
    public void seek(long visit) {
        int left = -1, right = data.size();
        while (right - left > 1) {
            int mid = ( left + right ) / 2;
            if (data.get(mid).first_visit < visit) {
                left = mid;
            } else {
                right = mid;
            }
        }
        this.idx = right;
    }
}

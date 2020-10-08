package jp4js.storage.dewey;

import java.util.ArrayList;
import jp4js.utils.iter.Iter;

public class IndexIterator implements Iter<IndexNode> {
    public IndexIterator(ArrayList<ArrayList<IndexNode>> nodesList) {
        this.data = nodesList;
    }

    @Override
    public IndexNode read() {
        return null;
    }

    @Override
    public void next() {
    }

    @Override
    public boolean valid() {
        return true;
    }
    

    private ArrayList<ArrayList<IndexNode>> data;
}

package jp4js.nf2.rel;

import java.util.List;
import java.util.Iterator;

public class DocumentSetList implements Iterable<DocumentSet> {
    private final List<DocumentSet> data;

    public DocumentSetList(List<DocumentSet> data) {
        this.data = data;
    }

    public Iterator<DocumentSet> iterator() {
        return this.data.iterator();
    }

    @Override
    public String toString() {
        String ret = "";
        for (DocumentSet tuple : this.data) {
            if (ret.length() != 0) {
                ret += ", ";
            }
            ret += tuple.toString();
        }
        return "[" + ret + "]";
    }
}
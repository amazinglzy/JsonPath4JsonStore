package jp4js.nf2.rel;

import java.util.List;
import java.util.Iterator;

public class DocumentSetList implements Iterable<Tuple> {
    private final List<Tuple> data;

    public DocumentSetList(List<Tuple> data) {
        this.data = data;
    }

    public Iterator<Tuple> iterator() {
        return this.data.iterator();
    }

    @Override
    public String toString() {
        String ret = "";
        for (Tuple tuple : this.data) {
            if (ret.length() != 0) {
                ret += ", ";
            }
            ret += tuple.toString();
        }
        return "[" + ret + "]";
    }
}
package jp4js.nf2.tpl;

import jp4js.nf2.DType;

import java.util.List;
import java.util.Iterator;

public class RepeatableDocumentSet implements DocumentSet, Iterable<DType.Instance> {
    private final List<DType.Instance> data;

    public RepeatableDocumentSet(List<DType.Instance> data) {
        this.data = data;
    }

    public Iterator<DType.Instance> iterator() {
        return this.data.iterator();
    }

    @Override
    public String toString() {
        String ret = "";
        for (DType.Instance tuple : this.data) {
            if (ret.length() != 0) {
                ret += ", ";
            }
            ret += tuple.toString();
        }
        return "[" + ret + "]";
    }
}
package jp4js.nf2.tpl;

import java.util.List;
import java.util.Iterator;

public class ListTuple implements DBody, Iterable<DBody> {
    private final List<DBody> data;

    public ListTuple(List<DBody> data) {
        this.data = data;
    }

    public Iterator<DBody> iterator() {
        return this.data.iterator();
    }

    @Override
    public String toString() {
        String ret = "";
        for (DBody tuple : this.data) {
            if (ret.length() != 0) {
                ret += ", ";
            }
            ret += tuple.toString();
        }
        return "[" + ret + "]";
    }
}
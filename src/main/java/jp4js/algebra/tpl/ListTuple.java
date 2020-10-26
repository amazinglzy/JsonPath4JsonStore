package jp4js.algebra.tpl;

import java.util.List;
import java.util.Iterator;

public class ListTuple implements NestedData, Iterable<NestedData> {
    private final List<NestedData> data;

    public ListTuple(List<NestedData> data) {
        this.data = data;
    }

    public Iterator<NestedData> iterator() {
        return this.data.iterator();
    }

    @Override
    public String toString() {
        String ret = "";
        for (NestedData tuple : this.data) {
            if (ret.length() != 0) {
                ret += ", ";
            }
            ret += tuple.toString();
        }
        return "[" + ret + "]";
    }
}
package jp4js.algebra.tpl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CrossListTuple implements NestedData {
    private List<List<NestedData>> data;
    private List<NestedData> actualData;

    public CrossListTuple() {
        this.data = new LinkedList<>();
    }

    public void add(List<NestedData> col) {
        this.data.add(col);
    }

    public Iterator<NestedData> actual() {
        this.actualData = new LinkedList<>() {{
            for (Tuple t: expand(data.iterator())) {
                add(t);
            }
        }};
        return this.actualData.iterator();
    }

    private List<Tuple> expand(Iterator<List<NestedData>> colIter) {
        List<Tuple> ret = new LinkedList<>();
        List<NestedData> col = colIter.next();
        if (colIter.hasNext()) {
            List<Tuple> right = expand(colIter);
            for (NestedData val: col) {
                for (Tuple r: right) {
                    Tuple t = new Tuple(r.size() + 1);
                    t.put(0, val);
                    for (int i = 1; i < t.size(); ++ i) {
                        t.put(i, r.get(i - 1));
                    }
                    ret.add(t);
                }
            }
        } else {
            for (NestedData val: col) {
                Tuple t = new Tuple(1);
                t.put(0, val);
                ret.add(t);
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        actual();
        return this.actualData.toString();
    }
}

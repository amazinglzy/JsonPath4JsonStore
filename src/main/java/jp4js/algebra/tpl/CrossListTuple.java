package jp4js.algebra.tpl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CrossListTuple implements DBody {
    private List<List<DBody>> data;
    private List<DBody> actualData;

    public CrossListTuple() {
        this.data = new LinkedList<>();
    }

    public void add(List<DBody> col) {
        this.data.add(col);
    }

    public Iterator<DBody> actual() {
        this.actualData = new LinkedList<>() {{
            for (Tuple t: expand(data.iterator())) {
                add(t);
            }
        }};
        return this.actualData.iterator();
    }

    private List<Tuple> expand(Iterator<List<DBody>> colIter) {
        List<Tuple> ret = new LinkedList<>();
        List<DBody> col = colIter.next();
        if (colIter.hasNext()) {
            List<Tuple> right = expand(colIter);
            for (DBody val: col) {
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
            for (DBody val: col) {
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

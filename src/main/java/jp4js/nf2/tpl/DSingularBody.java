package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class DSingularBody implements DBody {
    private final DType.Instance data;
    private final DBody[] tuple;

    public DSingularBody(int size) {
        this.data = null;
        this.tuple = new DBody[size];
    }

    public DSingularBody(DType.Instance data) {
        this.data = data;
        this.tuple = null;
    }

    public DSingularBody(DBody[] tuple) {
        this.data = null;
        this.tuple = tuple;
    }

    public DBody get(int i) {
        return this.tuple[i];
    }

    public void put(int i, DBody value) {
        this.tuple[i] = value;
    }

    public boolean isAtomic() {
        return this.data != null;
    }

    @Override
    public String toString() {
        String ret = "";
        if (this.isAtomic()) 
            ret = this.data.toString();
        else {
            for (int i = 0; i < this.tuple.length; i++) {
                if (i != 0) ret += ", ";
                ret += this.tuple[i].toString();
            }
            ret = "(" + ret + ")";
        }
        return ret;
    }
}
package jp4js.algebra.tpl;

import jp4js.algebra.Domain;

public class Tuple implements DBody {
    private final Domain.Instance data;
    private final DBody[] tuple;

    public Tuple(int size) {
        this.data = null;
        this.tuple = new DBody[size];
    }

    public Tuple(Domain.Instance data) {
        this.data = data;
        this.tuple = null;
    }

    public Tuple(DBody[] tuple) {
        this.data = null;
        this.tuple = tuple;
    }

    public DBody get(int i) {
        return this.tuple[i];
    }

    public boolean contains(int i) {
        return i < this.tuple.length;
    }

    public void put(int i, DBody value) {
        this.tuple[i] = value;
    }

    public boolean isAtomic() {
        return this.data != null;
    }

    public Domain.Instance data() {
        return this.data;
    }

    public int size() {
        return this.tuple.length;
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
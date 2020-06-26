package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class Tuple implements DType.Instance {
    private final DBody[] data;

    public Tuple(int size) {
        this.data = new DBody[size];
    }

    public Tuple(DBody[] data) {
        this.data = data;
    }

    public DBody get(int i) {
        return this.data[i];
    }

    public void put(int i, DBody value) {
        this.data[i] = value;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.data.length; i++) {
            if (i != 0) ret += ", ";
            ret += this.data[i].toString();
        }
        return ret;
    }
}
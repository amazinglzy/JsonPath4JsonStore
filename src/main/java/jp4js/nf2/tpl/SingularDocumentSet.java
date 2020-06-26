package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class SingularDocumentSet implements DocumentSet {
    private final DType.Instance[] data;

    public SingularDocumentSet(int size) {
        this.data = new DType.Instance[size];
    }

    public SingularDocumentSet(DType.Instance[] data) {
        this.data = data;
    }

    public DType.Instance get(int i) {
        return this.data[i];
    }

    public void put(int i, DType.Instance value) {
        this.data[i] = value;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.data.length; i++) {
            if (i != 0) ret += ", ";
            ret += this.data[i].toString();
        }
        return "(" + ret + ")";
    }
}
package jp4js.nf2.rel;

public class DocumentSet {
    private final Object[] data;

    public DocumentSet(Object[] data) {
        this.data = data;
    }

    public Object get(int i) {
        return this.data[i];
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
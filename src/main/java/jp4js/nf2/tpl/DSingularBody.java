package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class DSingularBody implements DBody {
    private final DType.Instance data;

    public DSingularBody(DType.Instance data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return '(' + this.data.toString() + ')';
    }
}
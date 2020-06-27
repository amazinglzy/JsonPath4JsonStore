package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class DSingularHeader extends DHeader{

    public DSingularHeader() {}
    public DSingularHeader(DType dType) {
        super(dType);
    }

    @Override
    public DHeaderType headerType() {
        return DHeaderType.SINGULAR;
    }

    @Override
    public String toString() {
        return '(' + super.toString() + ')';
    }
}
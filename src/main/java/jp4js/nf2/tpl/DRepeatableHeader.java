package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class DRepeatableHeader extends DHeader {
    private DRepeatableHeader header;

    public DRepeatableHeader() {
    }

    public DRepeatableHeader(DRepeatableHeader header) {
        this.header = header;
    }

    public DRepeatableHeader(DType dType) {
        super(dType);
    }

    public boolean isNested() {
        return this.header != null;
    }

    @Override
    public DHeaderType headerType() {
        return DHeaderType.REPEATABLE;
    }

    @Override
    public String toString() {
        if (this.isNested()) 
            return "[" + this.header.toString() + "]";
        return "[" + super.toString() + "]";
    }
}
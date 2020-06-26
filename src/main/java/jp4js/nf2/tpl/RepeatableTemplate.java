package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class RepeatableTemplate implements Template {
    private final DType elemType;

    public RepeatableTemplate(DType elemType) {
        this.elemType = elemType;
    }

    public DType elemType() {
        return this.elemType;
    }

    @Override
    public String toString() {
        return "[" + this.elemType.toString() + "]";
    }
}
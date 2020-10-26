package jp4js.algebra.tpl;

import jp4js.algebra.Domain;

public class AtomicTemplate implements Template {
    private Domain elemType;

    public AtomicTemplate(Domain elemType) {
        this.elemType = elemType;
    }

    public Domain elemType() {
        return this.elemType;
    }

    @Override
    public String toString() {
        return "";
        // return elemType.toString();
    }
}

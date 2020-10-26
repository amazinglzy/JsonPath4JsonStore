package jp4js.algebra.tpl;

import jp4js.algebra.Domain;

public class AtomicValue implements DBody {
    private Domain.Instance data;
    private Domain type;

    public AtomicValue(Domain type, Domain.Instance data) {
        this.type = type;
        this.data = data;
    }

    public Domain.Instance data() {
        return this.data;
    }

    public Domain type() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
}

package jp4js.algebra.tpl;

import jp4js.algebra.DType;

public class AtomicValue implements DBody {
    private DType.Instance data;
    private DType type;

    public AtomicValue(DType type, DType.Instance data) {
        this.type = type;
        this.data = data;
    }

    public DType.Instance data() {
        return this.data;
    }

    public DType type() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
}

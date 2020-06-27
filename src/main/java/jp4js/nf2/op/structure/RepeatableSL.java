package jp4js.nf2.op.structure;

public class RepeatableSL extends StructureList {
    private RepeatableSL lst;

    public RepeatableSL() {}

    public RepeatableSL(RepeatableSL lst) {
        this.lst = lst;
    }

    public boolean isNested() {
        return this.lst != null;
    }

    @Override
    public StructureType type() {
        return StructureType.REPEATABLE;
    }

    @Override
    public String toString() {
        if (this.isNested())
            return "[" + this.lst.toString() + "]";
        return "[" + super.toString() + "]";
    }
}
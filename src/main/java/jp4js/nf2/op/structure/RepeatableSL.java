package jp4js.nf2.op.structure;

public class RepeatableSL extends StructureList {
    private RepeatableSL lst;

    public RepeatableSL() {}

    public RepeatableSL(RepeatableSL lst) {
        this.lst = lst;
    }

    public RepeatableSL(SingularSL lst) {
        for (String fieldname: lst) {
            this.put(fieldname, lst.structure(fieldname), lst.rel(fieldname));
        }
    }

    public boolean isNested() {
        return this.lst != null;
    }

    public RepeatableSL elemType() {
        return this.lst;
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
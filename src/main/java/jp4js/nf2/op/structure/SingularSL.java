package jp4js.nf2.op.structure;

public class SingularSL extends StructureList {

    public SingularSL() {}
    public SingularSL(StructureList lst) {
        super(lst);
    }

    @Override
    public String toString() {
        return super.name() + "(" + super.toString() + ")";
    }
}
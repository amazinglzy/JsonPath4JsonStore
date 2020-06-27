package jp4js.nf2.op.structure;

public class SingularSL extends StructureList {
    @Override
    public StructureType type() {
        return StructureType.SINGULAR;
    }

    @Override
    public String toString() {
        return "(" + super.toString() + ")";
    }
}
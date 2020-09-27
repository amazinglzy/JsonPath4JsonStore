package jp4js.nf2.op.structure;

public class RepeatableSL extends StructureList {
    private StructureSteps steps;
    private StructureList lst;

    public RepeatableSL(StructureSteps steps, StructureList lst) {
        this.steps = steps;
        this.lst = lst;
    }

    public StructureList elemType() {
        return this.lst;
    }

    public StructureSteps steps() {
        return this.steps;
    }

    public void mergeIn(StructureList olst) {
        if (olst != null) {
            this.lst.mergeIn(olst);
        }
    }
    
    public void mergeIn(RepeatableSL olst) {
        if (olst != null) {
            this.lst.mergeIn(olst.lst);
        }
    }

    public void mergeTo(StructureList olst) {
        if (olst != null) {
            olst.mergeIn(this.lst);
        }
    }

    @Override
    public String toString() {
        return "[" + this.steps.toString() + ":" + this.lst.toString() + "]";
    }
}
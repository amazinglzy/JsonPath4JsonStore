package jp4js.nf2.op.structure;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class StructureSteps {
    public static abstract class Step {
        public StructureRelation rel;
        public Step(StructureRelation rel) {
            this.rel = rel; 
        }
    }

    public static class PropertyStep extends Step {
        public String fieldname;
        public PropertyStep(StructureRelation rel, String fieldname) {
            super(rel);
            this.fieldname = fieldname;
        }

        @Override
        public String toString() {return fieldname;}
    }

    public static class IndexStep extends Step {
        public int from;
        public int to;

        public IndexStep(int from, int to) {
            super(StructureRelation.PC);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "{" + from + "," + to + "}";
        }
    }

    private List<Step> steps;

    public StructureSteps() {
        steps = new ArrayList<>();
    }

    public void addStep(StructureRelation rel, String fieldname) {
        steps.add(new PropertyStep(rel, fieldname));
    }

    public void addStep(int from, int to) {
        steps.add(new IndexStep(from, to));
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public StructureRelation type(int index) {
        return this.steps.get(index).rel;
    }

    public Step step(int index) {
        return this.steps.get(index);
    }

    public int size() {
        return this.steps.size();
    }

    public void reverse() {
        Collections.reverse(this.steps);
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < steps.size(); i++) {
            ret += steps.get(i).rel == StructureRelation.PC ? "." : "..";
            ret += steps.get(i).toString();
        }
        return ret;
    }
}
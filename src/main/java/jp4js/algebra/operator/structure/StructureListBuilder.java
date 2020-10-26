package jp4js.algebra.operator.structure;

import java.util.ArrayDeque;
import java.util.Deque;

import jp4js.utils.Utils;

public class StructureListBuilder {
    private Deque<StructureList> path;
    private StructureList currentLst;
    private StructureSteps steps;

    public StructureListBuilder() {
        this.currentLst = new StructureList();
        this.path = new ArrayDeque<>() {{ add(StructureListBuilder.this.currentLst); }};
        this.steps = new StructureSteps();
    }

    public StructureListBuilder addStep(int from, int to) {
        this.steps.addStep(from, to);
        return this;
    }

    public StructureListBuilder addStep(StructureRelation rel, String fieldname) {
        this.steps.addStep(rel, fieldname);
        return this;
    }

    public StructureListBuilder addSteps(StructureSteps steps) {
        this.currentLst.addStep(steps);
        return this;
    }

    public StructureListBuilder enter() {
        StructureList lst = new StructureList();
        this.currentLst.put(this.steps, lst);
        this.path.push(lst);
        this.currentLst = lst;
        this.steps = new StructureSteps();
        return this;
    }

    public StructureListBuilder exit() {
        this.path.pop();
        this.currentLst = this.path.peek();
        return this;
    }

    public StructureList build() {
        Utils.isTrue(this.path.size() == 1, "Call Failed");
        return this.path.pop();
    }
}

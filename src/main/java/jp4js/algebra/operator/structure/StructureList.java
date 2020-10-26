package jp4js.algebra.operator.structure;

import java.util.Iterator;
import java.util.List;

import java.util.LinkedList;


public class StructureList implements Iterable<StructureList.StructureItem> {
    public class StructureItem {
        public StructureSteps steps;
        public StructureList lst;
    }

    private String name;
    private List<StructureItem> items;
    protected List<StructureSteps> stepLst;

    public StructureList() {
        this.name = "asy";
        this.items = new LinkedList<>();
        this.stepLst = new LinkedList<>();
    }

    public StructureList(String name) {
        this.name = name;
        this.items = new LinkedList<>();
        this.stepLst = new LinkedList<>();
    }

    public StructureList(StructureList lst) {
        this.name = lst.name;
        this.items = lst.items;
        this.stepLst = new LinkedList<>();
    }

    public StructureList(StructureSteps steps, StructureList lst) {
        this.name = lst.name;
        this.items = lst.items;
        this.stepLst = new LinkedList<>() {{
            add(steps);
        }};
    }

    public void put(String fieldname, StructureList s, StructureRelation rel) {
        StructureItem item = new StructureItem();
        item.steps = new StructureSteps();
        item.steps.addStep(rel, fieldname);
        item.lst = s;
        this.items.add(item);
    }

    public void put(StructureSteps steps, StructureList lst) {
        StructureItem item = new StructureItem();
        item.steps = steps;
        item.lst = lst;
        this.items.add(item);
    }

    public void put(StructureList.StructureItem item) {
        this.items.add(item);
    }

    public int size() {
        return this.items.size();
    }

    public String name() {
        return this.name;
    }

    public Iterator<StructureItem> iterator() {
        return items.iterator();
    }

    public void mergeIn(StructureList lst) {
        for (StructureItem item: lst) {
            this.items.add(item);
        }
    }

    public boolean isSingular() {
        return this.stepLst == null || this.stepLst.size() == 0;
    }

    public StructureSteps steps() {
        return this.stepLst.get(0);
    }

    public StructureList elemType() {
        return new StructureList(this);
    }

    public void addStep(StructureSteps steps) {
        this.stepLst.add(steps);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT { ");

        boolean notFirst = false;
        for (StructureItem item: this.items) {
            if (notFirst) {
                builder.append(", ");
            } else {
                notFirst = true;
            }

            builder.append(item.steps.toString());
            if (item.lst != null) {
                builder.append(": ");
                builder.append(item.lst.toString());
            }

        }

        builder.append(" }");

        if (!isSingular()) {
            builder.append(" NESTEDBY ");
            builder.append(this.stepLst.toString());
        }

        if (this.name != "asy") {
            builder.append(" AS ");
            builder.append(this.name);
        }

        return builder.toString();
    }
}
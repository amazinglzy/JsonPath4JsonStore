package jp4js.algebra.op.structure;

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

    public StructureList() {
        this.name = "asynomous";
        this.items = new LinkedList<>();
    }

    public StructureList(String name) {
        this.name = name;
        this.items = new LinkedList<>();
    }

    public StructureList(StructureList lst) {
        this.name = lst.name;
        this.items = lst.items;
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

    @Override
    public String toString() {
        String ret = "";
        for (StructureItem item: this.items) {
            if (ret.length() != 0) ret += ", ";

            ret += item.steps.toString();
            if (item.lst != null) {
                ret += ":" + item.lst.toString();
            }
        }
        return  ret;
    }
}
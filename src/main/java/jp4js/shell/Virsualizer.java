package jp4js.shell;

import jp4js.nf2.NestedRelation;
import jp4js.nf2.DType;
import jp4js.shell.ui.CharMatrixDrawer;
import jp4js.shell.layout.Horizontal;
import jp4js.shell.layout.Vertical;
import jp4js.shell.layout.WidthAllign;
import jp4js.shell.layout.Cell;

import java.util.TreeMap;

import java.util.Iterator;

public class Virsualizer {
    private final NestedRelation.Instance instance;
    private final NestedRelation relation;

    private NestedSharedWidth nestedSharedWidth;
    private CharMatrixDrawer drawer;
    private Vertical containers;

    public Virsualizer(NestedRelation.Instance instance) {
        this.instance = instance;
        this.relation = this.instance.relation();
        this.nestedSharedWidth = this.callNestedSharedWidth(this.relation);
        this.containers = new Vertical.Builder() {{
            add(
                Virsualizer.this.header(
                    Virsualizer.this.relation, 
                    Virsualizer.this.nestedSharedWidth
                )
            );
            for (NestedRelation.Tuple tuple: instance) 
                add(Virsualizer.this.tuple(tuple, Virsualizer.this.nestedSharedWidth));
        }}.build();
        this.drawer = new CharMatrixDrawer(containers.width());
        containers.draw(this.drawer, 0, 0);
    }

    private NestedSharedWidth callNestedSharedWidth(NestedRelation relation) {
        NestedSharedWidth ret = new NestedSharedWidth();
        for (String fieldname: relation) {
            DType type = relation.get(fieldname);
            if (type instanceof NestedRelation) {
                ret.put(fieldname, callNestedSharedWidth((NestedRelation)type));
            } else {
                ret.put(fieldname, new SharedAttr());
            }
        }
        return ret;
    }


    private Horizontal header(NestedRelation relation, NestedSharedWidth nestedSharedWidth) {
        return new Horizontal.Builder(nestedSharedWidth.width()) {{
            for (String fieldname: relation) {
                DType type = relation.get(fieldname);
                if (type instanceof NestedRelation) {
                    Vertical vertical = new Vertical.Builder() {{
                        add(new Cell.Builder(fieldname, nestedSharedWidth.get(fieldname).width()).build());
                        add(Virsualizer.this.header((NestedRelation)type, (NestedSharedWidth)nestedSharedWidth.get(fieldname)));
                    }}.build();
                    this.add(vertical);
                } else {
                    this.add(new Cell.Builder(fieldname, nestedSharedWidth.get(fieldname).width()).build());
                }
            }
        }}.build();
    }

    private Horizontal tuple(NestedRelation.Tuple tuple, NestedSharedWidth nestedSharedWidth) {
        return new Horizontal.Builder(nestedSharedWidth.width()) {{
            for (String fieldname: tuple.relation()) {
                Object value = tuple.get( tuple.relation().index(fieldname) );
                if (value == null) {
                    this.add(new Cell.Builder("", nestedSharedWidth.get(fieldname).width()).build());
                } else if (value instanceof NestedRelation.Instance) {
                    NestedRelation.Instance instance = (NestedRelation.Instance)value;
                    Vertical vertical = new Vertical.Builder() {{
                        for (NestedRelation.Tuple t: instance) {
                            add(Virsualizer.this.tuple(t, (NestedSharedWidth)nestedSharedWidth.get(fieldname)));
                        }
                    }}.build();
                    this.add(vertical);
                } else {
                    this.add(new Cell.Builder(value.toString(),  (nestedSharedWidth.get(fieldname)).width()).build());
                }
            }
        }}.build();
    }

    @Override
    public String toString() {
        return this.drawer.toString();
    }

    public static class  SharedAttr {
        private WidthAllign width;
        public SharedAttr() {
            this.width = new WidthAllign();
        }
        public WidthAllign width() {
            return this.width;
        }
    }

    public static class NestedSharedWidth extends SharedAttr implements Iterable<String> {
        private TreeMap<String, SharedAttr> mapping;
        public NestedSharedWidth() {
            this.mapping = new TreeMap<>();
        }

        public Iterator<String> iterator() {
            return this.mapping.keySet().iterator();
        }

        public SharedAttr get(String fieldname) {
            return this.mapping.get(fieldname);
        }

        public void put(String fieldname, SharedAttr sharedAttr) {
            this.mapping.put(fieldname, sharedAttr);
        }
    }
}
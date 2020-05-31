package jp4js.nf2;

import jp4js.utils.ui.CharMatrixDrawer;
import jp4js.utils.ui.Horizontal;
import jp4js.utils.ui.Vertical;
import jp4js.utils.ui.HomoCell;
import jp4js.utils.ui.HomoCell.SharedWidth;
import jp4js.utils.ui.Cell;

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
        this.containers = new Vertical() {{
            add(
                Virsualizer.this.header(
                    Virsualizer.this.relation, 
                    Virsualizer.this.nestedSharedWidth
                )
            );
        }};
        for (NestedRelation.Tuple tuple: instance) 
            this.containers.add(this.tuple(tuple, this.nestedSharedWidth));
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
                ret.put(fieldname, new SharedWidthWrapper());
            }
        }
        return ret;
    }


    private Horizontal header(NestedRelation relation, NestedSharedWidth nestedSharedWidth) {
        Horizontal ret = new Horizontal();
        for (String fieldname: relation) {
            DType type = relation.get(fieldname);
            if (type instanceof NestedRelation) {
                Vertical vertical = new Vertical();
                vertical.add(new Cell(fieldname));
                vertical.add(this.header((NestedRelation)type, (NestedSharedWidth)nestedSharedWidth.get(fieldname)));
                ret.add(vertical);
            } else {
                ret.add(new HomoCell(fieldname, ((SharedWidthWrapper)nestedSharedWidth.get(fieldname)).width()));
            }
        }
        return ret;
    }

    private Horizontal tuple(NestedRelation.Tuple tuple, NestedSharedWidth nestedSharedWidth) {
        Horizontal ret = new Horizontal();
        for (String fieldname: tuple.relation()) {
            Object value = tuple.get( tuple.relation().index(fieldname) );
            if (value instanceof NestedRelation.Instance) {
                NestedRelation.Instance instance = (NestedRelation.Instance)value;
                Vertical vertical = new Vertical();
                for (NestedRelation.Tuple t: instance) {
                    vertical.add(this.tuple(t, (NestedSharedWidth)nestedSharedWidth.get(fieldname)));
                }
                ret.add(vertical);
            } else {
                ret.add(new HomoCell(value.toString(),  ((SharedWidthWrapper)nestedSharedWidth.get(fieldname)).width()));
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return this.drawer.toString();
    }

    public interface SharedAttr {};
    public static class SharedWidthWrapper implements SharedAttr {
        public SharedWidth width;
        public SharedWidthWrapper() {
            this.width = new SharedWidth();
        }
        public SharedWidth width() {
            return this.width;
        }
    }

    public static class NestedSharedWidth implements SharedAttr, Iterable<String> {
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
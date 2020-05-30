package jp4js.nf2;

import jp4js.utils.ui.CharMatrixDrawer;
import jp4js.utils.Pair;

import java.util.List;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Virsualizer {
    private final NestedRelation.Instance instance;
    private final NestedRelation relation;
    private final NestedRelationTypeArrangement arrangement;
    private final String representation;
    public Virsualizer(NestedRelation.Instance instance) {
        this.instance = instance;
        this.relation = this.instance.relation();
        this.arrangement = this.buildArrangement(this.relation);
        for (NestedRelation.Tuple tuple: this.instance) {
            this.expandArrangementForTuple(tuple, this.arrangement);
        }
        this.representation = this.buildRepresentation();
    }

    @Override
    public String toString() {
        return this.representation;
    }

    public NestedRelationTypeArrangement arrangement() {
        return this.arrangement;
    }

    private String buildRepresentation() {
        int width = this.arrangement.data() + 2;
        CharMatrixDrawer drawer = new CharMatrixDrawer(width);
        List<Pair<Integer, Arrangement>> lst = new LinkedList<>() {{
            int currentPosition = 1;
            drawer.draw(1, 0, '|');
            for (String fieldname: Virsualizer.this.arrangement) {
                add(new Pair<>(currentPosition, Virsualizer.this.arrangement.get(fieldname)));
                int currentWidth = Virsualizer.this.arrangement.get(fieldname).data();
                drawer.drawHorizontalChar(0, currentPosition, '-', currentWidth);
                drawer.drawHorizontalString(1, currentPosition + 1, fieldname);
                drawer.draw(1, currentPosition + currentWidth, '|');
                currentPosition += currentWidth + 1;
            }
        }};
        int currentRow = 2;
        
        while (true) {
            boolean end = true;
            for (Pair<Integer, Arrangement> pair: lst) {
                Arrangement arrangement = pair.last();
                if (arrangement instanceof NestedRelationTypeArrangement) {
                    end = false;
                }
            }
            if (end) {
                for (Pair<Integer, Arrangement> pair: lst) {
                    drawer.drawHorizontalChar(
                        currentRow, 
                        pair.first(), 
                        '-',
                        pair.last().data());
                }
                break;
            } else {
                List<Pair<Integer, Arrangement>> newLst = new LinkedList<>();
                drawer.draw(currentRow + 1, 0, '|');
                for (Pair<Integer, Arrangement> pair: lst) {
                    Arrangement arrangement = pair.last();
                    if (arrangement instanceof NestedRelationTypeArrangement) {
                        int currentPosition = pair.first();
                        NestedRelationTypeArrangement nestedArrangement = (NestedRelationTypeArrangement)arrangement;
                        for (String fieldname: nestedArrangement) {
                            newLst.add(new Pair<>(currentPosition, nestedArrangement.get(fieldname)));
                            int currentWidth = nestedArrangement.get(fieldname).data();
                            drawer.drawHorizontalChar(currentRow, currentPosition, '-', nestedArrangement.get(fieldname).data());
                            drawer.drawHorizontalString(currentRow+1, currentPosition+1, fieldname);
                            drawer.draw(currentRow+1, currentPosition+currentWidth, '|');
                            currentPosition += currentWidth + 1;
                        }
                    } else {
                        newLst.add(pair);
                        drawer.draw(currentRow + 1, pair.first() + pair.last().data(), '|');
                    }
                }

                lst = newLst;
            }
        }

        return drawer.toString();
    }

    private NestedRelationTypeArrangement buildArrangement(NestedRelation relation) {
        NestedRelationTypeArrangement arrangement = new NestedRelationTypeArrangement(relation);
        for (String fieldname: relation) {
            DType type = relation.get(fieldname);
            if (type instanceof NestedRelation) {
                arrangement.put(fieldname, buildArrangement((NestedRelation)type));
            } else {
                arrangement.put(
                    fieldname, 
                    new Arrangement(fieldname.length() + 2, type));
            }
        }
        arrangement.update();
        return arrangement;
    }

    private void expandArrangementForTuple(NestedRelation.Tuple tuple, NestedRelationTypeArrangement arrangement) {
        NestedRelation relation = tuple.relation();
        for (String fieldname: relation) {
            int offset = relation.index(fieldname);
            Object value = tuple.get(offset);
            DType type = relation.get(fieldname);
            if (type instanceof NestedRelation) {
                if (!(value instanceof NestedRelation.Instance)) 
                    throw new IllegalArgumentException();
                NestedRelation.Instance instance = (NestedRelation.Instance)value;
                for (NestedRelation.Tuple nestedTuple: instance) {
                    if (!(arrangement.get(fieldname) instanceof NestedRelationTypeArrangement))
                        throw new IllegalArgumentException();
                    NestedRelationTypeArrangement nestedArrangement = (NestedRelationTypeArrangement)arrangement.get(fieldname);
                    expandArrangementForTuple(nestedTuple, nestedArrangement);
                }
                arrangement.update();
            } else {
                arrangement.get(fieldname).update(value.toString().length(), true);
            }
        }
    }

    public static class Arrangement implements Comparable<Arrangement> {
        public static int ATOMIC_COLUMN_WIDTH = 20;

        private int data;
        private DType type;
        public Arrangement(DType type) {
            this.data = 0;
            this.type = type;
        }
        public Arrangement(int data, DType type) {
            this.data = data;
            this.type = type;
        }

        public int data() {return this.data;}
        public void update(int data, boolean atomic) {
            if (atomic) {
                if (data > Arrangement.ATOMIC_COLUMN_WIDTH) 
                    this.data = ATOMIC_COLUMN_WIDTH;
                else if (data > this.data) 
                    this.data = data;
            } else {
                this.data = data;
            }
        }
        public DType type() {
            return this.type;
        }

        @Override
        public int compareTo(Arrangement o) {
            return 0;
        }
    }

    public static class NestedRelationTypeArrangement extends Arrangement implements Iterable<String> {
        private TreeMap<String, Arrangement> mapping;

        public NestedRelationTypeArrangement(NestedRelation type) {
            super(type);
            this.mapping = new TreeMap<>();
        }

        public void put(String fieldname, Arrangement arrangement) {
            this.mapping.put(fieldname, arrangement);
        }

        public Arrangement get(String fieldname) {
            return this.mapping.get(fieldname);
        }

        public Iterator<String> iterator() {
            return this.mapping.keySet().iterator();
        }

        public void update() {
            int newData = 0;
            for (String fieldname: mapping.keySet()) {
                newData += mapping.get(fieldname).data();
                newData += 1;
            }
            newData -= 1;
            this.update(newData, false);
        }

        public NestedRelation type() {
            return (NestedRelation)super.type();
        }
    }
}
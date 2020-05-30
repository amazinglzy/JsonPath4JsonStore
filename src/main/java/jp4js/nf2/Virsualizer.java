package jp4js.nf2;

import java.util.TreeMap;
import java.util.Iterator;

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
        return "";
    }

    private NestedRelationTypeArrangement buildArrangement(NestedRelation relation) {
        NestedRelationTypeArrangement arrangement = new NestedRelationTypeArrangement();
        int total = 0;
        for (String fieldname: relation) {
            DType type = relation.get(fieldname);
            if (type instanceof NestedRelation) {
                arrangement.put(fieldname, buildArrangement((NestedRelation)type));
            } else {
                arrangement.put(fieldname, new Arrangement(fieldname.length() + 2));
            }
            total += arrangement.get(fieldname).data();
            total += 1; // for column splitter
        }
        total -= 1; // for the last column splitter
        arrangement.update(total);
        return arrangement;
    }

    private void expandArrangementForTuple(NestedRelation.Tuple tuple, NestedRelationTypeArrangement arrangement) {
        
    }

    public static class Arrangement {
        private int data;
        public Arrangement() {this.data = 0;};
        public Arrangement(int data) {this.data = data;}
        public int data() {return this.data;}
        public void update(int data) {
            if (data > this.data) this.data = data;
        }
    }

    public static class NestedRelationTypeArrangement extends Arrangement implements Iterable<String> {
        private TreeMap<String, Arrangement> mapping;

        public NestedRelationTypeArrangement() {
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
    }
}
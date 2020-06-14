package jp4js.nf2.rel.doc;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;


public class Internal {
    public static abstract class InternalNode extends DocNode {
        public InternalNode(NodeID id) {
            super(id);
        }
    }
    
    public static class IndexNode extends InternalNode implements Iterable<DocNode> {
        private final ArrayList<DocNode> data;

        public IndexNode(NodeID id, ArrayList<DocNode> data) {
            super(id);
            this.data = data;
        }

        public DocNode get(int idx) {
            return this.data.get(idx);
        }

        public boolean contains(int idx) {
            return idx < this.data.size();
        }

        @Override
        public Iterator<DocNode> iterator() {
            return this.data.iterator();
        }

        @Override
        public String toString() {
            String ret = "";
            for (DocNode node: this.data) {
                if (ret.length() != 0) ret += ", ";
                ret += node.toString();
            }
            return "[" + this.id().toString() + ":" + ret + "]";
        }
    }

    public static class IndexNodeBuilder {
        private ArrayList<DocNode> data;

        public IndexNodeBuilder() {
            this.data = new ArrayList<>();
        }

        public void add(DocNode node) {
            this.data.add(node);
        }

        public IndexNode build(NodeID id) {
            return new IndexNode(id, data);
        }
    }

    public static class PropertyNode extends InternalNode implements Iterable<String> {
        private final TreeMap<String, DocNode> data;

        public PropertyNode(NodeID id, TreeMap<String, DocNode> data) {
            super(id);
            this.data = data;
        }

        public DocNode get(String fieldname) {
            return this.data.get(fieldname);
        }

        public boolean contains(String fieldname) {
            return this.data.containsKey(fieldname);
        }

        @Override
        public Iterator<String> iterator() {
            return this.data.keySet().iterator();
        }

        @Override
        public String toString() {
            String ret = "";
            for (String fieldname: this.data.keySet()) {
                if (ret.length() != 0) ret += ", ";
                ret += fieldname + "(" + this.data.get(fieldname).toString() + ")";
            }
            return "[" + this.id().toString() + ":" + ret + "]";
        }
    }

    public static class PropertyNodeBuilder {
        private TreeMap<String, DocNode> data;

        public PropertyNodeBuilder() {
            this.data = new TreeMap<>();
        }

        public void put(String fieldname, DocNode node) {
            this.data.put(fieldname, node);
        }

        public PropertyNode build(NodeID id) {
            return new PropertyNode(id, this.data);
        }
    }
}
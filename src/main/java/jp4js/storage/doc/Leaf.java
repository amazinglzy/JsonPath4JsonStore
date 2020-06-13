package jp4js.storage.doc;

import jp4js.nf2.rel.BasicType;
import jp4js.nf2.rel.BasicType.DDouble;
import jp4js.nf2.rel.BasicType.DInt;
import jp4js.nf2.rel.BasicType.DString;

public class Leaf {

    public static StringNode createStringNode(NodeID id, DString.Instance data) {
        return new StringNode(id, data);
    }

    public static StringNode createStringNode(NodeID id, String data) {
        return new StringNode(id, BasicType.createDString(data));
    }

    public static DoubleNode createDoubleNode(NodeID id, DDouble.Instance data) {
        return new DoubleNode(id, data);
    }

    public static DoubleNode createDoubleNode(NodeID id, double data) {
        return new DoubleNode(id, BasicType.createDDouble(data));
    }

    public static IntNode createIntNode(NodeID id, DInt.Instance data) {
        return new IntNode(id, data);
    }

    public static IntNode createIntNode(NodeID id, Integer data) {
        return new IntNode(id, BasicType.createDInt(data));
    }
    
    public static abstract class LeafNode extends DocNode {
        public LeafNode(NodeID id) {
            super(id);
        }
    }

    public static class StringNode extends LeafNode {
        private DString.Instance data;
        public StringNode(NodeID id, DString.Instance data) {
            super(id);
            this.data = data;
        }

        public DString.Instance data() {
            return this.data;
        }

        @Override
        public String toString() {
            return "[" + this.id().toString() + ":" + this.data.toString() + "]";
        }
    }

    public static class DoubleNode extends LeafNode {
        private DDouble.Instance data;
        public DoubleNode(NodeID id, DDouble.Instance data) {
            super(id);
            this.data = data;
        }

        public DDouble.Instance data() {
            return this.data;
        }

        @Override
        public String toString() {
            return "[" + this.id().toString() + ":" + this.data.toString() + "]";
        }
    }

    public static class IntNode extends LeafNode {
        private DInt.Instance data;
        public IntNode(NodeID id, DInt.Instance data) {
            super(id);
            this.data = data;
        }

        public DInt.Instance data() {
            return this.data;
        }

        @Override
        public String toString() {
            return "[" + this.id().toString() + ":" + this.data.toString() + "]";
        }
    }
}
package jp4js.nf2;


import java.util.List;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.Iterator;


public class Scalar {

    public static DString dString = new DString();
    public static DInt dInt = new DInt();
    public static DDouble dDouble = new DDouble();
    public static DMapping dMapping = new DMapping();
    public static DList dList = new DList();

    public static DString.Instance createDString(String data) {
        return dString.create(data);
    }

    public static DInt.Instance createDInt(int data) {
        return dInt.create(data);
    }

    public static DDouble.Instance createDDouble(double data) {
        return dDouble.create(data);
    }

    public static DMapping.Instance createDMapping() {
        return dMapping.create();
    }

    public static DList.Instance createDList() {
        return dList.create();
    }
    

    public static class DString implements DType {
        public class Instance implements DType.Instance {
            private final String data;

            public Instance(String data) {
                this.data = data;
            }

            public Instance(Instance dstring) {
                this.data = dstring.data();
            }

            public String data() {
                return this.data;
            }

            @Override
            public DString type() {
                return DString.this;
            }

            @Override
            public String toString() {
                return "\"" + this.data + "\""; 
            }
        }

        public Instance create(String data) {
            return new Instance(data);
        }

        @Override
        public String toString() {
            return "DString";
        }
    }

    public static class DInt implements DType {
        public class Instance implements DType.Instance {
            private final int data;

            public Instance(int data) {
                this.data = data;
            }

            public Instance(Instance dint) {
                this.data = dint.data();
            }

            public int data() {
                return this.data;
            }

            @Override
            public DInt type() {
                return DInt.this;
            }

            @Override
            public String toString() {
                return String.valueOf(this.data);
            }
        }

        public Instance create(int data) {
            return new Instance(data);
        }

        @Override
        public String toString() {
            return "DInt";
        }
    }

    public static class DDouble implements DType {
        public class Instance implements DType.Instance {
            private final double data;

            public Instance(double data) {
                this.data = data;
            }

            public Instance(Instance ddouble) {
                this.data = ddouble.data();
            }

            public double data() {
                return this.data;
            }

            @Override
            public DDouble type() {
                return DDouble.this;
            }

            @Override
            public String toString() {
                return String.valueOf(this.data);
            }
        }

        public Instance create(double data) {
            return new Instance(data);
        }

        @Override
        public String toString() {
            return "DDouble";
        }

    }

    public static class DMapping implements DType {
        public class Instance implements DType.Instance, Iterable<String> {
            private final TreeMap<String, DType.Instance> data;

            public Instance() {
                this.data = new TreeMap<>();
            }

            public Instance(TreeMap<String, DType.Instance> data) {
                this.data = data;
            }

            public void put(String field, DType.Instance data) {
                this.data.put(field, data);
            }

            public DType.Instance get(String field) {
                return this.data.get(field);
            }

            public boolean contains(String field) {
                return this.data.containsKey(field);
            }

            @Override
            public Iterator<String> iterator() {
                return this.data.keySet().iterator();
            }

            @Override
            public DMapping type() {
                return DMapping.this;
            }

            @Override
            public String toString() {
                return this.data.toString();
            }
        }

        public Instance create() {
            return new Instance();
        }

        @Override
        public String toString() {
            return "DMapping";
        }
    }

    public static class DList implements DType {
        public class Instance implements DType.Instance, Iterable<DType.Instance> {
            private final LinkedList<DType.Instance> data;

            public Instance() {
                this.data = new LinkedList<>();
            }

            public Instance(LinkedList<DType.Instance> data) {
                this.data = data;
            }

            public void add(DType.Instance item) {
                this.data.add(item);
            }

            public Iterator<DType.Instance> iterator() {
                return this.data.iterator();
            }

            public DType.Instance get(int index) {
                return this.data.get(index);
            }

            public List<DType.Instance> data() {
                return this.data;
            }

            @Override
            public DList type() {
                return DList.this;
            }

            @Override
            public String toString() {
                return this.data.toString();
            }
        }

        public Instance create() {
            return new Instance();
        }

        @Override
        public String toString() {
            return "DList";
        }
    }
}
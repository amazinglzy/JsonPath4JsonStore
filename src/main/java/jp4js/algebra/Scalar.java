package jp4js.algebra;


import java.util.List;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

import jp4js.algebra.tpl.AtomicValue;


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

    public static <T extends Domain.Instance> DList.Instance createDListValues(List<T> data) {
        DList.Instance ret = dList.create();
        for (T val: data) {
            ret.add(val);
        }
        return ret;
    }
    
    public static AtomicValue createAtomicString(String data) {
        Domain.Instance ins = dString.create(data);
        return new AtomicValue(dString, ins);
    }

    public static AtomicValue createAtomicInt(int data) {
        Domain.Instance ins = dInt.create(data);
        return new AtomicValue(dInt, ins);
    }

    public static AtomicValue createAtomicDouble(double data) {
        Domain.Instance ins = dDouble.create(data);
        return new AtomicValue(dDouble, ins);
    }

    public static AtomicValue createAtomicMapping() {
        Domain.Instance ins = dMapping.create();
        return new AtomicValue(dMapping, ins);
    }

    public static AtomicValue createAtomicList() {
        Domain.Instance ins = dList.create();
        return new AtomicValue(dList, ins);
    }

    public static class DString implements Domain {
        public class Instance implements Domain.Instance, Comparable<DString.Instance> {
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

            @Override
            public int compareTo(DString.Instance o) {
                return data.compareTo(o.data());
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof DString.Instance) {
                    return this.compareTo((DString.Instance)obj) == 0;
                }
                return false;
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

    public static class DInt implements Domain {
        public class Instance implements Domain.Instance, Comparable<DInt.Instance> {
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

            @Override
            public int compareTo(DInt.Instance o) {
                return this.data - o.data();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof DInt.Instance) {
                    return this.compareTo((DInt.Instance)obj) == 0;
                }
                return false;
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

    public static class DDouble implements Domain {
        public class Instance implements Domain.Instance, Comparable<DDouble.Instance> {
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

            @Override
            public int compareTo(DDouble.Instance o) {
                double c = this.data - o.data();
                if (c == 0) return 0;
                return c > 0 ? 1: -1;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof DDouble.Instance) {
                    return this.compareTo((DDouble.Instance)obj) == 0;
                }
                return false;
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

    public static class DMapping implements Domain {
        public class Instance implements Domain.Instance, Iterable<String>, Comparable<DMapping.Instance> {
            private final TreeMap<String, Domain.Instance> data;

            public Instance() {
                this.data = new TreeMap<>();
            }

            public Instance(TreeMap<String, Domain.Instance> data) {
                this.data = data;
            }

            public void put(String field, Domain.Instance data) {
                this.data.put(field, data);
            }

            public Domain.Instance get(String field) {
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

            @Override
            public int compareTo(DMapping.Instance o) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return true;
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

    public static class DList implements Domain {
        public class Instance implements Domain.Instance, Iterable<Domain.Instance>, Comparable<DList.Instance> {
            private final ArrayList<Domain.Instance> data;

            public Instance() {
                this.data = new ArrayList<>();
            }

            public Instance(LinkedList<Domain.Instance> data) {
                this.data = new ArrayList<>(data);
            }

            public void add(Domain.Instance item) {
                this.data.add(item);
            }

            public Iterator<Domain.Instance> iterator() {
                return this.data.iterator();
            }

            public Domain.Instance get(int index) {
                return this.data.get(index);
            }

            public List<Domain.Instance> data() {
                return this.data;
            }

            public int size() {
                return this.data.size();
            }

            @Override
            public DList type() {
                return DList.this;
            }

            @Override
            public String toString() {
                return this.data.toString();
            }

            @Override
            public int compareTo(DList.Instance o) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return true;
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
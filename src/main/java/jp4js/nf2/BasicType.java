package jp4js.nf2;

public class BasicType {

    public static DString dString = new DString();
    public static DInt dInt = new DInt();
    public static DDouble dDouble = new DDouble();

    public static DString.Instance createDString(String data) {
        return dString.create(data);
    }

    public static DInt.Instance createDInt(int data) {
        return dInt.create(data);
    }

    public static DDouble.Instance createDDouble(double data) {
        return dDouble.create(data);
    }
    

    public static class DString implements DType {
        public class Instance {
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
        public class Instance {
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
        public class Instance {
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
}
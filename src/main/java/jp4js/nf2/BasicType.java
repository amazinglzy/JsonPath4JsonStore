package jp4js.nf2;

public class BasicType {

    public static DString createDString(String data) {
        return new DString(data);
    }

    public static DInt createDInt(int data) {
        return new DInt(data);
    }

    public static DDouble createDDouble(double data) {
        return new DDouble(data);
    }
    

    public static class DString {
        private final String data;

        public DString(String data) {
            this.data = data;
        }

        public DString(DString dstring) {
            this.data = dstring.data();
        }

        public String data() {
            return this.data;
        }

        @Override
        public String toString() {
            return this.data;
        }
    }

    public static class DInt {
        private final int data;

        public DInt(int data) {
            this.data = data;
        }

        public DInt(DInt dint) {
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

    public static class DDouble {
        private final double data;

        public DDouble(double data) {
            this.data = data;
        }

        public DDouble(DDouble ddouble) {
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
}
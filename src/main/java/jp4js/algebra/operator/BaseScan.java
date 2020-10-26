package jp4js.algebra.operator;

public class BaseScan {
    public class MatchException extends Exception {
        public MatchException() {
            super();
        }

        public MatchException(String msg) {
            super(msg);
        }
    }
}
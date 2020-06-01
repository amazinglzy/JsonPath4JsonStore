package jp4js.query.path;

public class WildcardNode extends PathNode {
    @Override
    public String toString() {
        return "*" + super.toString();
    }
}
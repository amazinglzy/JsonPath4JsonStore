package jp4js.query.path;

public class Path {
    private RootNode root;
    public Path(RootNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "Path(" + root.toString() + ")";
    }
}
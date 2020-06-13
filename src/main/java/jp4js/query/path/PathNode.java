package jp4js.query.path;

import java.util.List;
import java.util.LinkedList;

public class PathNode {
    private List<PathNode> children;

    public PathNode() {
        this.children = new LinkedList<>();
    }

    public void add(PathNode node) {
        this.children.add(node);
    }

    public PathNode child(int i) {
        return this.children.get(i);
    }

    public List<PathNode> children() {
        return this.children;
    }

    @Override
    public String toString() {
        if (this.children.size() == 0) 
            return "";
        String ret = "";
        for (PathNode node: this.children) {
            if (ret.length() != 0) ret += ", ";
            ret += node.toString();
        }
        return "(" + ret + ")";
    }
}
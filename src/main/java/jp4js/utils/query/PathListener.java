package jp4js.utils.query;

import jp4js.query.path.Path;
import jp4js.query.path.PathNode;
import jp4js.query.path.RootNode;
import jp4js.query.path.PropertyNode;
import jp4js.query.path.ArrayNode;
import jp4js.query.path.WildcardNode;
import jp4js.query.path.DecendentNode;

import java.util.Deque;
import java.util.ArrayDeque;

public class PathListener extends PathBaseListener {
    private Deque<String> fieldPath;

    public PathListener(Path path) {
        super(path);
        this.fieldPath = new ArrayDeque<>();
    }

    public void enterRootNode(RootNode node) {
        this.fieldPath.push("$");
        if (this.isLeaf(node)) 
            this.visitLeafRootNode(node);
    }

    public void enterPropertyNode(PropertyNode node) {
        this.fieldPath.push(node.toString());
        if (this.isLeaf(node)) 
            this.visitLeafPropertyNode(node);
    }

    public void enterArrayNode(ArrayNode node) {
        this.fieldPath.push(node.toString());
        if (this.isLeaf(node)) 
            this.visitLeafArrayNode(node);
    }

    public void enterWildcardNode(WildcardNode node) {
        this.fieldPath.push(node.toString());
        if (this.isLeaf(node)) 
            this.visitLeafWildcardNode(node);
    }

    public void enterDecendentNode(DecendentNode node) {
        this.fieldPath.push(node.toString());
        if (this.isLeaf(node)) 
            this.visitLeafDecendentNode(node);
    }

    public void exitRootNode(RootNode node) {
        this.fieldPath.pop();
    }

    public void exitPropertyNode(PropertyNode node) {
        this.fieldPath.pop();
    }

    public void exitArrayNode(ArrayNode node) {
        this.fieldPath.pop();
    }

    public void exitWildcardNode(WildcardNode node) {
        this.fieldPath.pop();
    }

    public void exitDecendentNode(DecendentNode node) {
        this.fieldPath.pop();
    }

    public void visitLeafRootNode(RootNode node) {}
    public void visitLeafPropertyNode(PropertyNode node) {}
    public void visitLeafArrayNode(ArrayNode node) {}
    public void visitLeafWildcardNode(WildcardNode node) {}
    public void visitLeafDecendentNode(DecendentNode node) {}

    public String currentFieldname() {
        String ret = "";
        for (String fieldname: this.fieldPath) {
            if (ret.length() != 0) ret = "." + ret;
            ret = fieldname + ret;
        }
        return ret;
    }

    private boolean isLeaf(PathNode node) {
        if (node.children().size() == 0) return true;
        return false;
    }
}
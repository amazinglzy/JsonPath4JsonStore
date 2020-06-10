package jp4js.utils.query;

import jp4js.query.path.ArrayNode;
import jp4js.query.path.DecendentNode;
import jp4js.query.path.Path;
import jp4js.query.path.PathNode;
import jp4js.query.path.PropertyNode;
import jp4js.query.path.RootNode;
import jp4js.query.path.WildcardNode;

import java.util.List;


public class PathBaseListener {
    private Path path;

    public PathBaseListener(Path path) {
        this.path = path;
    }

    public void iterate() {
        this.iterate(this.path.root());
    }

    public void enterRootNode(RootNode node) {}

    public void enterPropertyNode(PropertyNode node) {}

    public void enterArrayNode(ArrayNode node) {}

    public void enterWildcardNode(WildcardNode node) {}

    public void enterDecendentNode(DecendentNode node) {}

    public void exitRootNode(RootNode node) {}

    public void exitPropertyNode(PropertyNode node) {}

    public void exitArrayNode(ArrayNode node) {}

    public void exitWildcardNode(WildcardNode node) {}

    public void exitDecendentNode(DecendentNode node) {}

    private void iterate(PathNode node) {
        if (node instanceof RootNode) {
            this.enterRootNode((RootNode)node);
            iterate(node.children());
            this.exitRootNode((RootNode)node);
            return;
        }
        if (node instanceof ArrayNode) {
            this.enterArrayNode((ArrayNode)node);
            iterate(node.children());
            this.exitArrayNode((ArrayNode)node);
            return;
        }
        if (node instanceof PropertyNode) {
            this.enterPropertyNode((PropertyNode)node);
            iterate(node.children());
            this.exitPropertyNode((PropertyNode)node);
            return;
        }
        if (node instanceof WildcardNode) {
            this.enterWildcardNode((WildcardNode)node);
            iterate(node.children());
            this.exitWildcardNode((WildcardNode)node);
            return;
        }
        if (node instanceof DecendentNode) {
            this.enterDecendentNode((DecendentNode)node);
            iterate(node.children());
            this.exitDecendentNode((DecendentNode)node);
            return;
        }
        throw new IllegalArgumentException();
    }

    private void iterate(List<PathNode> nodes) {
        for (PathNode node: nodes) {
            this.iterate(node);
        }
    }

}
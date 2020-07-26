package jp4js.query.path;

import java.util.List;

import jp4js.storage.node.LabelArray.ArraySelections;

import java.util.ArrayDeque;
import java.util.Deque;

public class PathBuilder {
    private Deque<PathNode> path;

    public PathBuilder() {
        this.path = new ArrayDeque<>() {{
            push(new RootNode());
        }};
    }

    public PathBuilder enterPropertyNode(List<String> properties) {
        PropertyNode node = new PropertyNode(properties);
        this.path.peek().add(node);
        this.path.push(node);
        return this;
    }

    public PathBuilder enterDecendentNode(List<String> properties) {
        DecendentNode node = new DecendentNode(properties);
        this.path.peek().add(node);
        this.path.push(node);
        return this;
    }

    public PathBuilder enterArrayNode(ArraySelections selections) {
        ArrayNode node = new ArrayNode(selections);
        this.path.peek().add(node);
        this.path.push(node);
        return this;
    }

    public PathBuilder exit() {
        this.path.pop();
        return this;
    }

    public RootNode build() {
        if (this.path.size() != 1) 
            throw new IllegalArgumentException();
        return (RootNode)this.path.pop();
    }
}
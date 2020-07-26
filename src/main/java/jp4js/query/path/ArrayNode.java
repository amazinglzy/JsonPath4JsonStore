package jp4js.query.path;

import jp4js.storage.node.LabelArray.ArraySelections;

public class ArrayNode extends PathNode {
    private ArraySelections selections;

    public ArrayNode(ArraySelections selections) {
        this.selections = selections;
    }

    public ArraySelections selections() {
        return this.selections;
    }

    @Override
    public String toString() {
        return this.selections.toString() + super.toString();
    }
}
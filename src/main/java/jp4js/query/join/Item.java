package jp4js.query.join;

import jp4js.index.node.LabelNode;

import java.util.LinkedList;
import java.util.List;

public class Item {
    private LabelNode data;
    private List<Integer> arraySelectionIndices;

    public Item(LabelNode data) {
        this.data = data;
        this.arraySelectionIndices = new LinkedList<>();
    }

    public Item(LabelNode data, Integer idx) {
        this.data = data;
        this.arraySelectionIndices = new LinkedList<>() {{
            add(idx);
        }};
    }

    public Item(LabelNode data, List<Integer> arraySelectionIndices) {
        this.arraySelectionIndices = arraySelectionIndices;
    }

    public LabelNode getData() {
        return data;
    }

    public List<Integer> getArraySelectionIndices() {
        return this.arraySelectionIndices;
    }
}
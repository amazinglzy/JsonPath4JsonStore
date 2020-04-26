package jp4js.query.join;

import jp4js.index.node.LabelNode;

import java.util.Comparator;
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
        this.data = data;
        this.arraySelectionIndices = arraySelectionIndices;
    }

    public LabelNode getData() {
        return data;
    }

    public List<Integer> getArraySelectionIndices() {
        return this.arraySelectionIndices;
    }

    public static Comparator<Item> comparator() {
        return new Comparator<Item>() {
            public int compare(Item i1, Item i2) {
                return i1.getData().compareTo(i2.getData());
            }
        };
    }
}
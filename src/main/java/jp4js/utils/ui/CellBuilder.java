package jp4js.utils.ui;

public class CellBuilder {
    private String data;
    private SharedWidth width;
    public CellBuilder(String data, SharedWidth width) {
        this.data = data;
        this.width = width;
    }

    public Cell build() {
        Cell ret = new Cell(data, width);
        ret.update();
        return ret;
    }
}
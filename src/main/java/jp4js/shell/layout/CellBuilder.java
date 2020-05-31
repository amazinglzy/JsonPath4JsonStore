package jp4js.shell.layout;

public class CellBuilder {
    private String data;
    private WidthAllign width;
    public CellBuilder(String data, WidthAllign width) {
        this.data = data;
        this.width = width;
    }

    public Cell build() {
        Cell ret = new Cell(data, width);
        ret.update();
        return ret;
    }
}
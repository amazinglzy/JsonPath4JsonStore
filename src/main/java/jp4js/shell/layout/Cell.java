package jp4js.shell.layout;

import jp4js.shell.ui.CharMatrixDrawer;

public class Cell extends BaseContainer {
    private String data;
    public Cell(String data) {
        this.data = data;
    }

    public Cell(String data, WidthAllign width) {
        super(width);
        this.data = data;
    }

    @Override
    public int actualWidth() {
        return this.data.length() + 2;
    }

    @Override
    public int actualHeight() {
        return 1;
    }

    @Override
    public void draw(CharMatrixDrawer drawer, int sx, int sy) {
        drawer.drawHorizontalString(sx, sy + 1, this.data);
    }
}
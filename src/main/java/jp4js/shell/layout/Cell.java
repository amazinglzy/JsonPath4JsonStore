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

    public static class Builder {
        private String data;
        private WidthAllign widthAllign;
        public Builder(String data, WidthAllign widthAllign) {
            this.data = data;
            this.widthAllign = widthAllign;
        }

        public Cell build() {
            Cell ret = new Cell(data, widthAllign);
            ret.update();
            return ret;
        }
    }
}
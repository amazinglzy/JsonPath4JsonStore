package jp4js.utils.ui;

public class Cell implements Container {
    private String data;
    public Cell(String data) {
        this.data = data;
    }

    @Override
    public int width() {
        return this.data.length() + 2;
    }

    @Override
    public int height() {
        return 1;
    }

    @Override
    public void draw(CharMatrixDrawer drawer, int sx, int sy) {
        drawer.drawHorizontalString(sx, sy + 1, this.data);
    }
}
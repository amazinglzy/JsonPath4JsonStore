package jp4js.utils.ui;

public class ContainerMocker implements Container {
    private int width, height;

    public ContainerMocker(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public void draw(CharMatrixDrawer drawer, int sx, int sy) {
        return;
    }
}
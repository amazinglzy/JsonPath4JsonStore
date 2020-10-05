package jp4js.query.shell.layout;

import jp4js.query.shell.ui.CharMatrixDrawer;


public class ContainerMocker extends BaseContainer {
    private int width, height;

    public ContainerMocker(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int actualWidth() {
        return this.width;
    }

    @Override
    public int actualHeight() {
        return this.height;
    }

    @Override
    public void draw(CharMatrixDrawer drawer, int sx, int sy) {
        return;
    }
}
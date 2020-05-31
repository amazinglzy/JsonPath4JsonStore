package jp4js.shell.layout;

import jp4js.shell.ui.CharMatrixDrawer;


public class ContainerMocker extends Allignment {
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
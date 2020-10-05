package jp4js.query.shell.layout;

import jp4js.query.shell.ui.CharMatrixDrawer;

public interface Container {
    public int width();
    public int height();
    public void draw(CharMatrixDrawer drawer, int sx, int sy);
}
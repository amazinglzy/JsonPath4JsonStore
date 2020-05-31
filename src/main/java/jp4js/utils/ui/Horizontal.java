package jp4js.utils.ui;

import java.util.List;

public class Horizontal extends Allignment {
    private final List<Container> containers;

    public Horizontal(List<Container> containers) {
        this.containers = containers;
    }

    public Horizontal(List<Container> containers, SharedWidth width) {
        super(width);
        this.containers = containers;
    }

    @Override
    public int actualWidth() {
        int width = 1;
        for (Container container: this.containers) {
            width += container.width() + 1;
        }
        if (width == 1) width += 2;
        return width;
    }

    @Override
    public int actualHeight() {
        int height = 1 + 1 + 1;
        for (Container container: this.containers) {
            if (1 + container.height() + 1 > height) {
                height = 1 + container.height() + 1;
            }
        }
        return height;
    }

    @Override
    public void draw(CharMatrixDrawer drawer, int sx, int sy) {
        drawer.drawVerticalChar(sx + 1, sy, '|', this.height() - 2);
        int offsetY = 1;
        for (Container container: this.containers) {
            drawer.drawHorizontalChar(sx, sy + offsetY, '-', container.width());
            drawer.drawHorizontalChar(sx + this.height() - 1, sy + offsetY, '-', container.width());
            container.draw(drawer, sx + 1, sy + offsetY);
            drawer.drawVerticalChar(sx + 1, sy + offsetY + container.width(), '|', this.height() - 2);
            offsetY += container.width() + 1;
        }
    }
}
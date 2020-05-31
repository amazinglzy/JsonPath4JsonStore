package jp4js.shell.layout;

import jp4js.shell.ui.CharMatrixDrawer;
import java.util.List;

public class Vertical extends Allignment{
    private final List<Container> containers;

    public Vertical(List<Container> containers) {
        this.containers = containers;
    }

    public Vertical(List<Container> containers, WidthAllign width) {
        super(width);
        this.containers = containers;
    }

    @Override
    public int actualWidth() {
        int width = 1 + 1 + 1;
        for (Container container: containers) {
            if (1 + container.width() + 1 > width)
                width = 1 + container.width() + 1;
        }
        return width;
    }

    @Override
    public int actualHeight() {
        int height = 1;
        for (Container container: containers) {
            height += container.height() + 1;
        }
        if (height == 1) height += 2;
        return height;
    }

    @Override
    public void draw(CharMatrixDrawer drawer, int sx, int sy) {
        drawer.drawHorizontalChar(sx, sy + 1, '-', this.width() - 2);
        int offsetX = 1;
        for (Container container: this.containers) {
            drawer.drawVerticalChar(sx + offsetX, sy, '|', container.height());
            drawer.drawVerticalChar(sx + offsetX, sy + this.width() - 1, '|', container.height());
            container.draw(drawer, sx + offsetX, sy + 1);
            drawer.drawHorizontalChar(sx + offsetX + container.height(), sy + 1, '-', this.width() - 2);
            offsetX += container.height() + 1;
        }
    }
}
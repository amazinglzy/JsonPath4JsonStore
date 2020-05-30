package jp4js.utils.ui;

import java.util.List;
import java.util.LinkedList;

public class Horizontal implements Container {
    private List<Container> containers;

    public Horizontal() {
        this.containers = new LinkedList<>();
    }

    public void add(Container container) {
        this.containers.add(container);
    }

    @Override
    public int width() {
        int width = 1;
        for (Container container: this.containers) {
            width += container.width() + 1;
        }
        if (width == 1) width += 2;
        return width;
    }

    @Override
    public int height() {
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
        drawer.drawVerticalChar(sx + 1, sy + this.width() - 1, '|', this.height() - 2);
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
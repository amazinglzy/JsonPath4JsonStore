package jp4js.utils.ui;

public class HorizontalBuilder extends ContainersBuilder {
    public HorizontalBuilder() {}
    public HorizontalBuilder(SharedWidth width) {
        super(width);
    }

    @Override
    public Horizontal build() {
        Horizontal ret = new Horizontal(this.containers(), this.width());
        ret.update();
        return ret;
    }
}
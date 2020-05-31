package jp4js.utils.ui;

public class VerticalBuilder extends ContainersBuilder {
    @Override
    public Vertical build() {
        Vertical ret = new Vertical(this.containers(), this.width());
        ret.update();
        return ret;
    }
}
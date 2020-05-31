package jp4js.utils.ui;

public class HomoCell extends Cell {
    private SharedWidth sharedWidth;

    public HomoCell(String data, SharedWidth sharedWidth) {
        super(data);
        this.sharedWidth = sharedWidth;
        this.sharedWidth.update(super.width());
    }

    @Override
    public int width() {
        return this.sharedWidth.data();
    }

    public static class SharedWidth {
        private int data;
        public SharedWidth() {
            this.data = 0;
        }

        public int data() {
            return this.data;
        }

        public void update(int data) {
            if (data > this.data)
                this.data = data;
        }
    }
}
package jp4js.shell.layout;

public class WidthAllign {
    private int data;
    public WidthAllign() {
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
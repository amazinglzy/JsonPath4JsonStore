package jp4js.shell.ui;

public class SharedWidth {
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
package jp4js.shell.ui;

import java.util.ArrayList;
import java.util.List;

public class CharMatrixDrawer {
    private int width;
    private List<char[] > data;

    public CharMatrixDrawer(int width) {
        this.width = width;
        this.data = new ArrayList<>();
    }

    public void draw(int i, int j, char c) {
        if (j >= this.width) 
            throw new IllegalArgumentException();
        while (i >= this.data.size()) {
            this.newRow();
        }
        this.data.get(i)[j] = c;
    }

    public void drawHorizontalChar(int r, int c, char ch, int repeatNum) {
        for (int i = 0; i < repeatNum; i++) 
            draw(r, c + i, ch);
    }

    public void drawVerticalChar(int r, int c, char ch, int repeatNum) {
        for (int i = 0; i < repeatNum; i++) 
            draw(r + i, c, ch);
    }

    public void drawHorizontalString(int r, int c, String s) {
        for (int i = 0; i < s.length(); i++) 
            draw(r, c + i, s.charAt(i));
    }

    public void drawVerticalString(int r, int c, String s) {
        for (int i = 0; i < s.length(); i++)
            draw(r + i, c, s.charAt(i));
    }

    private void newRow() {
        char[] row = new char[this.width];
        for (int i = 0; i < this.width; i++) 
            row[i] = ' ';
        this.data.add(row);
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.data.size(); i++) {
            if (ret.length() != 0) ret += "\n";
            ret += new String(this.data.get(i));
        }
        return ret;
    }
}
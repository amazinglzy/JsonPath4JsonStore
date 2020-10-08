package jp4js.utils.iter;

import java.util.ArrayList;

public class ArrayIter<E> implements Iter<E> {
    public ArrayIter(ArrayList<E> data) {
        this.data = data;
        this.cursor = 0;
    }

    @Override
    public E read() {
        return this.data.get(cursor);
    }

    @Override
    public void next() {
        this.cursor ++;
    }

    @Override
    public boolean valid() {
        return this.cursor < this.data.size();
    }

    private ArrayList<E> data;
    private int cursor;
}

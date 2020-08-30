package jp4js.utils.iter;

import java.util.Iterator;

public class Iter2Iterator<E> implements Iterator<E> {
    private Iter<E> iter;

    public Iter2Iterator(Iter<E> iter) {
        this.iter = iter;
    }

    @Override
    public E next() {
        E ret = this.iter.read();
        this.iter.next();
        return ret;
    }

    @Override
    public boolean hasNext() {
        return this.iter.valid();
    }
}
package jp4js.utils.iter;

import java.util.Comparator;


public class TwoIter<E> implements Iter<E> {
    private Iter<E> iter1, iter2;
    private Comparator<E> c;
    
    public TwoIter(Iter<E> iter1, Iter<E> iter2, Comparator<E> c) {
        this.iter1 = iter1;
        this.iter2 = iter2;
        this.c = c;
    }

    @Override
    public E read() {
        if (this.iter1.valid() && this.iter2.valid()) {
            E n1 = this.iter1.read();
            E n2 = this.iter2.read();
            if (c.compare(n1, n2) < 0) return n1;
            else return n2;
        } else if (this.iter1.valid()) {
            return this.iter1.read();
        } else {
            return this.iter2.read();
        }
    }

    @Override
    public void next() {
        if (this.iter1.valid() && this.iter2.valid()) {
            E n1 = this.iter1.read();
            E n2 = this.iter2.read();
            if (c.compare(n1, n2) < 0) this.iter1.next();
            else this.iter2.next();
        } else if (this.iter1.valid()) {
            this.iter1.next();
        } else {
            this.iter2.next();
        }
    }

    @Override
    public boolean valid() {
        return (this.iter1.valid() || this.iter2.valid());
    }
}
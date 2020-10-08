package jp4js.utils.iter;

import jp4js.utils.filter.Filter;

public class FilterIter<E> implements Iter<E> {
    private Iter<E> iter;
    private Filter<E> filter;

    public FilterIter(Iter<E> iter, Filter<E> filter) {
        this.iter = iter;
        this.filter = filter;
    }

    private void skip() {
        while (this.iter.valid()) {
            if (!filter.accept(this.iter.read()))  {
                this.iter.next();
            } else {
                return;
            }
        }
    }

    @Override
    public E read() {
        skip();
        return this.iter.read();
    }

    @Override
    public void next() {
        skip();
        this.iter.next();
    }

    @Override
    public boolean valid() {
        skip();
        return this.iter.valid();
    }
}
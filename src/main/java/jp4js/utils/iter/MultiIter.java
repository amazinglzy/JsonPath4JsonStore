package jp4js.utils.iter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MultiIter<E> implements Iter<E> {
    public MultiIter(ArrayList<Iter<E>> iters, Comparator<E> cmp) {
        this.iters = iters;
        this.buf = new PriorityQueue<>();
        this.cmp = cmp;

        for (Iter<E> iter: this.iters) {
            if (iter.valid()) {
                this.buf.add(new Item(iter.read(), iter));
            }
        }
    }

    @Override
    public E read() {
        return this.buf.peek().data;
    }

    @Override
    public void next() {
        Item item = this.buf.poll();
        item.iter.next();
        if (item.iter.valid()) {
            item.data = item.iter.read();
            this.buf.add(item);
        }
    }

    @Override
    public boolean valid() {
        return this.buf.size() > 0;
    }

    public class Item implements Comparable<Item> {
        public E data;
        public Iter<E> iter;

        public Item(E data, Iter<E> iter) {
            this.data = data;
            this.iter = iter;
        }

        @Override
        public int compareTo(Item item) {
            return MultiIter.this.cmp.compare(this.data, item.data);
        }
    }

    private ArrayList<Iter<E>> iters;
    private PriorityQueue<Item> buf;
    private Comparator<E> cmp;
}

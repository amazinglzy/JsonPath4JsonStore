package jp4js.utils;

public class Pair<T extends Comparable<T>, E extends Comparable<E>> implements Comparable<Pair<T, E>>{
    private T first;
    private E last;

    public Pair(T first, E last) {
        this.first = first;
        this.last = last;
    }

    public T first() {
        return this.first;
    }

    public E last() {
        return this.last;
    }

    @Override
    public int compareTo(Pair<T, E> other) {
        if (this.first.compareTo(other.first()) != 0) 
            return this.first.compareTo(other.first());
        return this.last.compareTo(other.last());
    }
}
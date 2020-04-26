package jp4js.utils.iter;

public class EmptyIter<E> implements Iter<E> {

    public EmptyIter() {
    }
    
    @Override
    public E read() {
        throw new IllegalArgumentException();
    }

    @Override
    public void next() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Iter<E> cloneCurrentIterator() {
        return new EmptyIter<>();
    }
}
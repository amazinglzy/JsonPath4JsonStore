package jp4js.utils.iter;

public interface Iter<E> {
    /*
    return the current node of the iterator
     */
    E read();

    /*

     */
    void next();

    /*

     */
    boolean hasNext();

    Iter<E> cloneCurrentIterator();
}

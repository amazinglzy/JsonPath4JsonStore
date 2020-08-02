package jp4js.utils.iter;

public interface Iter<E> {
    /*
    E read();
    void next();
    boolean hasNext();
    */
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

    void seek(long visit);
}

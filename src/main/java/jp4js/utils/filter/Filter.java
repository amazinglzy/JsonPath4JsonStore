package jp4js.utils.filter;

public interface Filter<E> {
    boolean accept(E e);
}
package jp4js.utils.filter;

public class OrFilter<E> implements Filter<E> {
    private Filter<E> f1,f2;

    public OrFilter(Filter<E> f1, Filter<E> f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public boolean accept(E e) {
        return this.f1.accept(e) || this.f2.accept(e);
    }
}
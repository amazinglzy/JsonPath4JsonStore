package jp4js.nf2.tpl;

public class DSingularHeader extends DHeader{

    public DSingularHeader() {}

    @Override
    public DHeaderType headerType() {
        return DHeaderType.SINGULAR;
    }

    @Override
    public String toString() {
        return '(' + super.toString() + ')';
    }
}
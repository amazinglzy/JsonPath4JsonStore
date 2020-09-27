package jp4js.nf2.tpl;

public class ListTemplate implements DHeader {
    private DHeader header;

    public ListTemplate() {

    }

    public ListTemplate(DHeader header) {
        this.header = header;
    }

    public void setHeader(DHeader header) {
        this.header = header;
    }

    public DHeader getHeader() {
        return this.header;
    }

    @Override
    public String toString() {
        return "[" + header.toString() + "]";
    }
}

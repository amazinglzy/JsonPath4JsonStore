package jp4js.algebra.tpl;

public class ListTemplate implements Template {
    private Template header;

    public ListTemplate() {

    }

    public ListTemplate(Template header) {
        this.header = header;
    }

    public void setHeader(Template header) {
        this.header = header;
    }

    public Template getHeader() {
        return this.header;
    }

    @Override
    public String toString() {
        return "[" + header.toString() + "]";
    }
}

package jp4js.nf2.tpl;

import jp4js.utils.Utils;

import java.util.Deque;
import java.util.ArrayDeque;

public class DHeaderBuilder {
    private Deque<DHeader> headerPath;
    private Deque<String> fieldPath;

    public DHeaderBuilder() {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.headerPath.push(new DRepeatableHeader());
    }

    public DHeaderBuilder(DHeaderType type) {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        switch(type) {
            case SINGULAR:
                this.headerPath.push(new DSingularHeader());
                break;
            case REPEATABLE:
                this.headerPath.push(new DRepeatableHeader());
                break;
            default:
                Utils.CanNotBeHere("Unknown DHeaderType");
        }
    }

    public DHeaderBuilder put(String fieldname) {
        DHeader dHeader = this.headerPath.peek();
        dHeader.put(fieldname, null);
        return this;
    }

    public DHeaderBuilder enter(String fieldname) {
        this.headerPath.push(new DRepeatableHeader());
        this.fieldPath.push(fieldname);
        return this;
    }

    public DHeaderBuilder exit() {
        if (this.headerPath.size() <= 1) 
            throw new IllegalArgumentException();
        DHeader header = this.headerPath.pop();
        DHeader cHeader = this.headerPath.peek();
        cHeader.put(this.fieldPath.pop(), header);
        return this;
    }

    public DHeader build() {
        if (this.headerPath.size() != 1) 
            throw new IllegalArgumentException();
        return this.headerPath.pop();
    }
}
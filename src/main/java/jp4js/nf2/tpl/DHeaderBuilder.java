package jp4js.nf2.tpl;

import jp4js.nf2.DType;

import java.util.Deque;
import java.util.ArrayDeque;

public class DHeaderBuilder {
    private Deque<DHeader> headerPath;
    private Deque<String> fieldPath;

    public DHeaderBuilder() {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.headerPath.push(new DHeader(DHeaderType.REPEATABLE));
    }

    public DHeaderBuilder(DHeaderType type) {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.headerPath.push(new DHeader(type));
    }

    public DHeaderBuilder put(String fieldname, DType type) {
        DHeader dHeader = this.getOrReplaceCurrentUnatomicHeader();
        dHeader.put(fieldname, new DHeader(DHeaderType.SINGULAR, type));
        return this;
    }

    public DHeaderBuilder put(DType type) {
        DHeader header = this.headerPath.pop();
        this.headerPath.push(new DHeader(header.headerType(), type));
        return this;
    }

    public DHeaderBuilder enter(String fieldname) {
        this.getOrReplaceCurrentUnatomicHeader();
        this.headerPath.push(new DHeader(DHeaderType.REPEATABLE));
        this.fieldPath.push(fieldname);
        return this;
    }

    public DHeaderBuilder exit() {
        if (this.headerPath.size() <= 1) 
            throw new IllegalArgumentException();
        DHeader header = this.headerPath.pop();
        DHeader cHeader = this.getOrReplaceCurrentUnatomicHeader();
        cHeader.put(this.fieldPath.pop(), header);
        return this;
    }

    public DHeader build() {
        if (this.headerPath.size() != 1) 
            throw new IllegalArgumentException();
        return this.headerPath.pop();
    }

    private DHeader getOrReplaceCurrentUnatomicHeader() {
        DHeader dHeader = this.headerPath.peek();
        if (dHeader.isAtomic()) {
            this.headerPath.pop();
            dHeader = new DHeader(dHeader.headerType());
            this.headerPath.push(dHeader);
        }
        return dHeader;
    }
}
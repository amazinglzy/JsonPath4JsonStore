package jp4js.algebra.tpl;

import jp4js.algebra.DType;
import jp4js.utils.Utils;

import java.util.Deque;
import java.util.ArrayDeque;

public class DHeaderBuilder {
    private Deque<DHeader> headerPath;
    private Deque<String> fieldPath;

    public DHeaderBuilder() {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.headerPath.push(new ListTemplate());
        this.headerPath.push(new Template());
    }

    public DHeaderBuilder(DHeaderType type) {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        switch(type) {
            case SINGULAR:
                this.headerPath.push(new Template());
                break;
            case REPEATABLE:
                this.headerPath.push(new ListTemplate());
                this.headerPath.push(new Template());
                break;
            default:
                Utils.CanNotBeHere("Unknown DHeaderType");
        }
    }

    public DHeaderBuilder put(String fieldname, DType type) {
        DHeader header = this.headerPath.peek();
        Utils.isTrue(header instanceof Template, "Must be Template not ListTemplate");
        Template tpl = (Template)header;
        tpl.put(fieldname, new AtomicTemplate(type));
        return this;
    }

    public DHeaderBuilder enter(String fieldname, int nestedLevel) {
        Utils.isTrue(nestedLevel >= 0, "Nested Level Must be greater than 0");
        this.fieldPath.push(fieldname);
        for (int i = 0; i < nestedLevel; i ++) {
            this.headerPath.push(new ListTemplate());
        }
        this.headerPath.push(new Template());
        return this;
    }

    public DHeaderBuilder exit() {
        Utils.isTrue(this.headerPath.size() > 1, "the size of headerPath must be greater than 2");
        reduce();
        return this;
    }

    public DHeader build() {
        Utils.isTrue(this.headerPath.size() > 1, "the size of headerPath must be greater than 2");
        reduce();
        Utils.isTrue(this.headerPath.size() == 1, "Must be 1 in the path");
        return this.headerPath.pop();
    }

    private void reduce() {
        while (this.headerPath.size() > 1) {
            DHeader header = this.headerPath.pop();
            DHeader cHeader = this.headerPath.pop();

            if (cHeader instanceof ListTemplate) {
                ListTemplate tpl = (ListTemplate)cHeader;
                tpl.setHeader(header);
                this.headerPath.push(tpl);
                continue;
            }

            if (cHeader instanceof Template) {
                Template tpl = (Template)cHeader;
                tpl.put(this.fieldPath.pop(), header);
                this.headerPath.push(tpl);
                break;
            }
        }
        if (this.headerPath.size() > 1) {
            Utils.isTrue(this.headerPath.peek() instanceof Template, "top of the path must be Template");
        }
    }
}
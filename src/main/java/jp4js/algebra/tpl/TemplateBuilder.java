package jp4js.algebra.tpl;

import jp4js.algebra.Domain;
import jp4js.utils.Utils;

import java.util.Deque;
import java.util.ArrayDeque;

public class TemplateBuilder {
    private Deque<Template> headerPath;
    private Deque<String> fieldPath;

    public TemplateBuilder() {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.headerPath.push(new ListTemplate());
        this.headerPath.push(new DictTemplate());
    }

    public TemplateBuilder(TemplateType type) {
        this.headerPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        switch(type) {
            case SINGULAR:
                this.headerPath.push(new DictTemplate());
                break;
            case REPEATABLE:
                this.headerPath.push(new ListTemplate());
                this.headerPath.push(new DictTemplate());
                break;
            default:
                Utils.CanNotBeHere("Unknown DHeaderType");
        }
    }

    public TemplateBuilder put(String fieldname, Domain type) {
        Template header = this.headerPath.peek();
        Utils.isTrue(header instanceof DictTemplate, "Must be Template not ListTemplate");
        DictTemplate tpl = (DictTemplate)header;
        tpl.put(fieldname, new AtomicTemplate(type));
        return this;
    }

    public TemplateBuilder enter(String fieldname, int nestedLevel) {
        Utils.isTrue(nestedLevel >= 0, "Nested Level Must be greater than 0");
        this.fieldPath.push(fieldname);
        for (int i = 0; i < nestedLevel; i ++) {
            this.headerPath.push(new ListTemplate());
        }
        this.headerPath.push(new DictTemplate());
        return this;
    }

    public TemplateBuilder exit() {
        Utils.isTrue(this.headerPath.size() > 1, "the size of headerPath must be greater than 2");
        reduce();
        return this;
    }

    public Template build() {
        Utils.isTrue(this.headerPath.size() > 1, "the size of headerPath must be greater than 2");
        reduce();
        Utils.isTrue(this.headerPath.size() == 1, "Must be 1 in the path");
        return this.headerPath.pop();
    }

    private void reduce() {
        while (this.headerPath.size() > 1) {
            Template header = this.headerPath.pop();
            Template cHeader = this.headerPath.pop();

            if (cHeader instanceof ListTemplate) {
                ListTemplate tpl = (ListTemplate)cHeader;
                tpl.setHeader(header);
                this.headerPath.push(tpl);
                continue;
            }

            if (cHeader instanceof DictTemplate) {
                DictTemplate tpl = (DictTemplate)cHeader;
                tpl.put(this.fieldPath.pop(), header);
                this.headerPath.push(tpl);
                break;
            }
        }
        if (this.headerPath.size() > 1) {
            Utils.isTrue(this.headerPath.peek() instanceof DictTemplate, "top of the path must be Template");
        }
    }
}
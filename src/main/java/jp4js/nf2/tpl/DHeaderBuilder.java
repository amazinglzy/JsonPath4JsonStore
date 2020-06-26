package jp4js.nf2.tpl;

import jp4js.nf2.DType;

import java.util.Deque;
import java.util.ArrayDeque;

public class DHeaderBuilder {
    private Deque<DType> mappingPath;
    private Deque<DHeaderType> templatePath;
    private Deque<String> fieldPath;

    public DHeaderBuilder() {
        this.mappingPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.templatePath = new ArrayDeque<>();

        this.mappingPath.push(new FixedMapping());
        this.templatePath.push(DHeaderType.REPEATABLE);
    }

    public DHeaderBuilder(DHeaderType type) {
        this.mappingPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.templatePath = new ArrayDeque<>();

        this.mappingPath.push(new FixedMapping());
        this.templatePath.push(type);
    }

    public DHeaderBuilder put(String fieldname, DType type) {
        FixedMapping fixedMapping = this.getOrReplaceCurrentFixedMapping();

        DHeader template = new DSingularHeader(type);
        fixedMapping.put(fieldname, template);

        return this;
    }

    public DHeaderBuilder put(DType type) {
        this.mappingPath.pop();
        this.mappingPath.push(type);
        return this;
    }

    public DHeaderBuilder enter(String fieldname) {
        this.getOrReplaceCurrentFixedMapping();
        this.mappingPath.push(new FixedMapping());
        this.fieldPath.push(fieldname);
        this.templatePath.push(DHeaderType.REPEATABLE);
        return this;
    }

    public DHeaderBuilder exit() {
        if (this.mappingPath.size() <= 1) 
            throw new IllegalArgumentException();
        DType type = this.mappingPath.pop();
        DHeader template;
        switch(this.templatePath.pop()) {
            case SINGULAR:
                template = new DSingularHeader(type);
                break;
            case REPEATABLE:
                template = new DRepeatableHeader(type);
                break;
            default:
                throw new IllegalArgumentException();
        }
        FixedMapping mapping = this.getOrReplaceCurrentFixedMapping();
        mapping.put(this.fieldPath.pop(), template);
        return this;
    }

    public DHeader build() {
        if (this.mappingPath.size() != 1) 
            throw new IllegalArgumentException();
        DType type = this.mappingPath.pop();
        switch(this.templatePath.pop()) {
            case SINGULAR:
                return new DSingularHeader(type);
            case REPEATABLE:
                return new DRepeatableHeader(type);
            default:
                throw new IllegalArgumentException();
        }
    }

    private FixedMapping getOrReplaceCurrentFixedMapping() {
        DType cType = this.mappingPath.peek();
        FixedMapping fixedMapping;
        if (cType instanceof FixedMapping) {
            fixedMapping = (FixedMapping)cType;
        } else {;
            this.mappingPath.pop();
            fixedMapping = new FixedMapping();
            this.mappingPath.push(fixedMapping);
        }
        return fixedMapping;
    }
}
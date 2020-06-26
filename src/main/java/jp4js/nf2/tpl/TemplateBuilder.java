package jp4js.nf2.tpl;

import jp4js.nf2.DType;

import java.util.Deque;
import java.util.ArrayDeque;

public class TemplateBuilder {
    public static enum TemplateType {
        SINGULAR, REPEATABLE
    }

    private Deque<DType> mappingPath;
    private Deque<TemplateType> templatePath;
    private Deque<String> fieldPath;

    public TemplateBuilder() {
        this.mappingPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.templatePath = new ArrayDeque<>();

        this.mappingPath.push(new FixedMapping());
        this.templatePath.push(TemplateType.REPEATABLE);
    }

    public TemplateBuilder(TemplateType type) {
        this.mappingPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.templatePath = new ArrayDeque<>();

        this.mappingPath.push(new FixedMapping());
        this.templatePath.push(type);
    }

    public TemplateBuilder put(String fieldname, DType type) {
        FixedMapping fixedMapping = this.getOrReplaceCurrentFixedMapping();

        Template template = new SingularTemplate(type);
        fixedMapping.put(fieldname, template);

        return this;
    }

    public TemplateBuilder enter(String fieldname) {
        this.getOrReplaceCurrentFixedMapping();
        this.mappingPath.push(new FixedMapping());
        this.fieldPath.push(fieldname);
        this.templatePath.push(TemplateType.REPEATABLE);
        return this;
    }

    public TemplateBuilder exit() {
        if (this.mappingPath.size() <= 1) 
            throw new IllegalArgumentException();
        DType type = this.mappingPath.pop();
        Template template;
        switch(this.templatePath.pop()) {
            case SINGULAR:
                template = new SingularTemplate(type);
                break;
            case REPEATABLE:
                template = new RepeatableTemplate(type);
                break;
            default:
                throw new IllegalArgumentException();
        }
        FixedMapping mapping = this.getOrReplaceCurrentFixedMapping();
        mapping.put(this.fieldPath.pop(), template);
        return this;
    }

    public Template build() {
        if (this.mappingPath.size() != 1) 
            throw new IllegalArgumentException();
        DType type = this.mappingPath.pop();
        switch(this.templatePath.pop()) {
            case SINGULAR:
                return new SingularTemplate(type);
            case REPEATABLE:
                return new RepeatableTemplate(type);
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
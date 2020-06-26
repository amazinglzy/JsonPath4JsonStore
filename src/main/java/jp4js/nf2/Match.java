package jp4js.nf2;

import jp4js.nf2.tpl.DHeader;
import jp4js.nf2.tpl.DHeaderType;

import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.DRepeatableBody;
import jp4js.nf2.tpl.DSingularBody;
import jp4js.nf2.tpl.DSingularHeader;
import jp4js.nf2.tpl.DRepeatableHeader;

public class Match {
    private DHeader template;
    private DBody documentSet;
    private boolean valid;

    public Match(DHeader template, DBody documentSet) {
        this.template = template;
        this.documentSet = documentSet;
        this.valid = this.tryMatch(this.template, this.documentSet);
    }

    public boolean isValid() {
        return this.valid;
    }

    private boolean tryMatch(DHeader template, DBody documentSet) {
        DHeaderType type = this.getTemplateType(template,documentSet);
        switch (type) {
            case SINGULAR:
                return this.tryMatchSingular(
                    (DSingularHeader)template, 
                    (DSingularBody)documentSet);
            case REPEATABLE:
                return this.tryMatchRepeatable(
                    (DRepeatableHeader)template, 
                    (DRepeatableBody)documentSet);
            default:
                throw new IllegalArgumentException();
        }
    }

    private DHeaderType getTemplateType(DHeader template, DBody documentSet) {
        if (template instanceof DSingularHeader && documentSet instanceof DSingularBody) 
            return DHeaderType.SINGULAR;
        if (template instanceof DRepeatableHeader && documentSet instanceof DRepeatableBody)
            return DHeaderType.REPEATABLE;
        throw new IllegalArgumentException();
    }

    private boolean tryMatchSingular(DSingularHeader template, DSingularBody documentSet) {
        return false;
    }

    private boolean tryMatchRepeatable(DRepeatableHeader template, DRepeatableBody documentSet) {
        return false;
    }
}
package jp4js.nf2;

import jp4js.nf2.tpl.DHeader;
import jp4js.nf2.tpl.DHeaderType;

import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.DRepeatableBody;
import jp4js.nf2.tpl.DSingularBody;

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
        return false;
    }

}
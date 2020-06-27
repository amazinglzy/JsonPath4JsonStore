package jp4js.nf2;

import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.DHeader;
import jp4js.nf2.tpl.DRepeatableBody;
import jp4js.nf2.tpl.DSingularBody;
import jp4js.utils.Utils;

public class Match {
    private DHeader template;
    private DBody documentSet;
    private boolean valid;

    public Match(DHeader template, DBody documentSet) {
        this.template = template;
        this.documentSet = documentSet;
        this.valid = this.tryMatch(this.template, this.documentSet, true);
    }

    public boolean isValid() {
        return this.valid;
    }

    private boolean tryMatch(DHeader header, DBody documentSet, boolean typeSense) {
        if (documentSet == null) return true;
        if (typeSense) {
            switch(header.headerType()) {
                case SINGULAR:
                    if (!(documentSet instanceof DSingularBody))
                        return false;
                    break;
                case REPEATABLE:
                    if (!(documentSet instanceof DRepeatableBody))
                        return false;
                    break;
                default:
                    Utils.CanNotBeHere("DHeader unkown type");
            }
        }
        if (documentSet instanceof DSingularBody)
            return tryMatchSingular(header, (DSingularBody)documentSet);
        if (documentSet instanceof DRepeatableBody)
            return tryMatchRepeatable(header, (DRepeatableBody)documentSet);
        Utils.CanNotBeHere("Unkown DBody Type");
        return false;
    }

    private boolean tryMatchSingular(DHeader header, DSingularBody body) {
        if (header.isAtomic() != body.isAtomic()) 
            return false;
        if (header.isAtomic() && body.isAtomic()) {
            return header.dType() == body.data().type();
        } else {
            for (String fieldname: header) {
                if (body.contains(header.index(fieldname))) {
                    if (!this.tryMatch(header.get(fieldname), body.get(header.index(fieldname)), true)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean tryMatchRepeatable(DHeader header, DRepeatableBody body) {
        for (DBody dBody: body) {
            if (!tryMatch(header, dBody, false))
                return false;
        }
        return true;
    }
}
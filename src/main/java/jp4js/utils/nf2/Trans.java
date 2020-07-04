package jp4js.utils.nf2;

import jp4js.nf2.tpl.DHeader;
import jp4js.nf2.tpl.DRepeatableHeader;
import jp4js.nf2.tpl.DSingularHeader;
import jp4js.utils.Utils;
import jp4js.nf2.op.structure.RepeatableSL;
import jp4js.nf2.op.structure.SingularSL;
import jp4js.nf2.op.structure.StructureList;

public class Trans {
    public static DHeader fromSL(StructureList lst) {
        if (lst instanceof SingularSL)
            return fromSL((SingularSL)lst);
        else if (lst instanceof RepeatableSL) 
            return fromSL((RepeatableSL)lst);
        else 
            Utils.CanNotBeHere("Unkown StructureList Type");
        return null;
    }

    private static DSingularHeader fromSL(SingularSL lst) {
        DSingularHeader ret = new DSingularHeader();
        for (String fieldname: lst) {
            StructureList nestedLst = lst.structure(fieldname);
            if (nestedLst != null) 
                ret.put(fieldname, Trans.fromSL(nestedLst));
            else
                ret.put(fieldname, null);
        }
        return ret;
    }

    private static DRepeatableHeader fromSL(RepeatableSL lst) {
        if (lst.isNested()) {
            return new DRepeatableHeader(
                Trans.fromSL(lst.elemType())
            );
        } else {
            DRepeatableHeader ret = new DRepeatableHeader();
            for (String fieldname: lst) {
                StructureList nestedLst = lst.structure(fieldname);
                if (nestedLst != null) 
                    ret.put(fieldname, Trans.fromSL(nestedLst));
                else
                    ret.put(fieldname, null);
            }
            return ret;
        }
    }
}
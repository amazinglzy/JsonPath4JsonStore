package jp4js.utils.nf2;

import jp4js.nf2.tpl.DHeader;
import jp4js.nf2.tpl.DRepeatableHeader;
import jp4js.nf2.tpl.DSingularHeader;
import jp4js.utils.Configuration;
import jp4js.utils.Utils;
import jp4js.nf2.DType;
import jp4js.nf2.Scalar;
import jp4js.nf2.Scalar.DList;
import jp4js.nf2.Scalar.DMapping;
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
        for (StructureList.StructureItem item: lst) {
            String fieldname = item.steps.fieldname(0);
            StructureList nestedLst = item.lst;
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
            for (StructureList.StructureItem item: lst) {
                String fieldname = item.steps.fieldname(0);
                StructureList nestedLst = item.lst;
                if (nestedLst != null) 
                    ret.put(fieldname, Trans.fromSL(nestedLst));
                else
                    ret.put(fieldname, null);
            }
            return ret;
        }
    }

    public static DType.Instance fromJSON(Object json, Configuration configuration) {
        if (configuration.jsonProvider().isMap(json)) {
            DMapping.Instance ret = Scalar.createDMapping();
            for (String fieldname: configuration.jsonProvider().getPropertyKeys(json)) {
                ret.put(
                    fieldname, 
                    fromJSON(
                        configuration.jsonProvider().getMapValue(json, fieldname),
                        configuration
                    ));
            }
            return ret;
        }
        if (configuration.jsonProvider().isArray(json)) {
            DList.Instance ret = Scalar.createDList();
            for (Object subJson: configuration.jsonProvider().toIterable(json)) {
                ret.add(fromJSON(subJson, configuration));
            }
            return ret;
        }
        Object value = configuration.jsonProvider().unwrap(json);
        if (value instanceof Integer) 
            return Scalar.createDInt((Integer)value);
        if (value instanceof String)
            return Scalar.createDString((String)value);
        if (value instanceof Double)
            return Scalar.createDDouble((Double)value);
        Utils.CanNotBeHere("unsupported type");
        return null;
    }
}
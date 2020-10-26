package jp4js.utils.algebra;

import jp4js.algebra.tpl.DictTemplate;
import jp4js.algebra.tpl.ListTemplate;
import jp4js.algebra.tpl.AtomicTemplate;
import jp4js.algebra.tpl.Template;
import jp4js.utils.Utils;
import jp4js.algebra.Domain;
import jp4js.algebra.Scalar;
import jp4js.algebra.Scalar.DList;
import jp4js.algebra.Scalar.DMapping;
import jp4js.algebra.op.structure.StructureList;

import com.jayway.jsonpath.Configuration;

public class Trans {
    public static Template fromSL(StructureList lst) {
        DictTemplate ret = new DictTemplate();
        for (StructureList.StructureItem item: lst) {
            String fieldname = item.steps.toString();
            StructureList nestedLst = item.lst;
            if (nestedLst != null) 
                ret.put(fieldname, Trans.fromSL(nestedLst));
            else
                ret.put(fieldname, new AtomicTemplate(null));
        }
        if (lst.isSingular())
            return ret;
        return new ListTemplate(ret);
    }

    public static Domain.Instance fromJSON(Object json, Configuration configuration) {
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
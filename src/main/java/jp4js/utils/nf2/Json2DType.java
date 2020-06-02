package jp4js.utils.nf2;

import java.util.List;

import jp4js.nf2.rel.BasicType;
import jp4js.nf2.rel.DType;
import jp4js.utils.Configuration;
import jp4js.nf2.rel.NestedRelationBuilder;

public class Json2DType {
    private List<Object> jsonLst;
    private Configuration configuration;

    public Json2DType(List<Object> jsonLst, Configuration configuration) {
        this.jsonLst = jsonLst;
        this.configuration = configuration;
    }

    public DType relation() {
        NestedRelationBuilder builder = new NestedRelationBuilder();
        builder.enter("$");
        for (Object json: jsonLst) update("", json, builder);
        builder.exit();
        return builder.build().get("$");
    }

    public void update(String fieldname, Object json, NestedRelationBuilder builder) {
        if (configuration.jsonProvider().isArray(json)) {
            builder.enter(fieldname);
                for (Object nestedJson: configuration.jsonProvider().toIterable(json)) {
                    update("", nestedJson, builder);
                }
            builder.exit();
        } else if (configuration.jsonProvider().isMap(json)) {
            for (String field: configuration.jsonProvider().getPropertyKeys(json)) {
                update(
                    ( fieldname.length() != 0 ) ?  fieldname + "." + field: field, 
                    configuration.jsonProvider().getMapValue(json, field), 
                    builder);
            }
        } else {
            Object value = configuration.jsonProvider().unwrap(json);
            if (value instanceof String) {
                builder.put(
                    ( fieldname.length() != 0 ) ?  fieldname + "." + "[str]": "[str]", 
                    BasicType.dString
                );
            } else if (value instanceof Integer) {
                builder.put(
                    ( fieldname.length() != 0 ) ?  fieldname + "." + "[int]": "[int]", 
                    BasicType.dInt
                );
            } else if (value instanceof Boolean) {
                throw new IllegalArgumentException();
            } else if (value instanceof Double) {
                builder.put(
                    ( fieldname.length() != 0 ) ?  fieldname + "." + "[double]": "[double]", 
                    BasicType.dDouble
                );
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
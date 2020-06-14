package jp4js.utils;

import jp4js.nf2.rel.doc.DocNode;
import jp4js.nf2.rel.doc.Internal;
import jp4js.nf2.rel.doc.Leaf;
import jp4js.nf2.rel.doc.NodeID;

public class TransUtils {
    public static DocNode trans(Object json, Configuration configuration) {
        DocNode node;
        if (configuration.jsonProvider().isMap(json)) {
            node = new Internal.PropertyNodeBuilder() {{
                for (String fieldname: configuration.jsonProvider().getPropertyKeys(json)) {
                    put(
                        fieldname,
                        trans(
                            configuration.jsonProvider().getMapValue(json, fieldname),
                            configuration
                        )
                    );
                }
            }}.build(new NodeID(0, 0));
        } else if (configuration.jsonProvider().isArray(json)) {
            node = new Internal.IndexNodeBuilder() {{
                for (Object nestedJson: configuration.jsonProvider().toIterable(json)) {
                    add(trans(nestedJson, configuration));
                }
            }}.build(new NodeID(0, 0));
        } else {
            Object value = configuration.jsonProvider().unwrap(json);
            if (value instanceof Number) {
                if (value instanceof Double) {
                    node = Leaf.createDoubleNode(new NodeID(0, 0), (Double)value);
                } else if (value instanceof Integer) {
                    node = Leaf.createIntNode(new NodeID(0, 0), (Integer)value);
                } else {
                    throw new IllegalArgumentException(value.toString());
                }
            } else if (value instanceof String) {
                node = Leaf.createStringNode(new NodeID(0, 0), (String)value);
            } else if (value instanceof Boolean) {
                throw new IllegalArgumentException();
            } else {
                throw new IllegalArgumentException();
            }
        }
        return node;
    }
}
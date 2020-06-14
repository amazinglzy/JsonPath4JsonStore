package jp4js.utils.nf2;

import java.util.List;

import jp4js.nf2.rel.BasicType;
import jp4js.nf2.rel.NestedRelationBuilder;
import jp4js.nf2.rel.doc.DocNode;
import jp4js.nf2.rel.doc.Internal;
import jp4js.nf2.rel.doc.Leaf;

public class Json2DType {
    public Json2DType(List<DocNode> jsonLst, String base, NestedRelationBuilder builder) {
        for (DocNode node: jsonLst) update(base, node, builder);
    }

    public void update(String fieldname, DocNode node, NestedRelationBuilder builder) {
        if (node instanceof Internal.IndexNode) {
            builder.enter(fieldname);
                for (DocNode nestedNode: (Internal.IndexNode)node) {
                    update("", nestedNode, builder);
                }
            builder.exit();
        } else if (node instanceof Internal.PropertyNode) {
            for (String field: (Internal.PropertyNode)node) {
                update(
                    ( fieldname.length() != 0 ) ?  fieldname + "." + field: field, 
                    ((Internal.PropertyNode)node).get(field), 
                    builder);
            }
        } else {
            if (node instanceof Leaf.StringNode) {
                builder.put(
                    ( fieldname.length() != 0 ) ?  fieldname + "." + "[str]": "[str]", 
                    BasicType.dString
                );
            } else if (node instanceof Leaf.IntNode) {
                builder.put(
                    ( fieldname.length() != 0 ) ?  fieldname + "." + "[int]": "[int]", 
                    BasicType.dInt
                );
            } else if (node instanceof Leaf.DoubleNode) {
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
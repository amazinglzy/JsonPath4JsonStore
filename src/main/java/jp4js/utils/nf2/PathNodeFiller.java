package jp4js.utils.nf2;

import jp4js.nf2.rel.NestedRelation;
import jp4js.nf2.rel.doc.DocNode;
import jp4js.nf2.rel.doc.Internal;
import jp4js.nf2.rel.doc.Leaf;
import jp4js.utils.Utils;

public class PathNodeFiller {
    private NestedRelation.InstanceBuilder builder;
    private String base;

    public PathNodeFiller(String base, NestedRelation.InstanceBuilder builder) {
        this.base = base;
        this.builder = builder;
    }

    public void fill(DocNode node) {
        this.iterate(this.base, node);
    }

    public void iterate(String field, DocNode node) {
        if (node instanceof Leaf.DoubleNode) {
            Leaf.DoubleNode doubleNode = (Leaf.DoubleNode)node;
            this.builder.put(Utils.append(field, "[double]", "."), doubleNode.data());
        }

        if (node instanceof Leaf.IntNode) {
            Leaf.IntNode intNode = (Leaf.IntNode)node;
            this.builder.put(Utils.append(field, "[int]", "."), intNode.data());
        }

        if (node instanceof Leaf.StringNode) {
            Leaf.StringNode stringNode = (Leaf.StringNode)node;
            this.builder.put(Utils.append(field, "[str]", "."), stringNode.data());
        }

        if (node instanceof Internal.PropertyNode) {
            Internal.PropertyNode propertyNode = (Internal.PropertyNode)node;
            for (String subfield: propertyNode) {
                iterate(Utils.append(field, subfield, "."), propertyNode.get(subfield));
            }
        } 
        
        if (node instanceof Internal.IndexNode) {
            Internal.IndexNode indexNode = (Internal.IndexNode)node;
            this.builder.enter(field);
            for (DocNode sNode: indexNode) {
                this.builder.begin();
                this.iterate("", sNode);
                this.builder.end();
            }
            this.builder.exit();
        }
    }
}
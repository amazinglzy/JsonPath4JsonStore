package jp4js.utils.nf2;

import jp4js.nf2.rel.doc.DocNode;
import jp4js.nf2.rel.doc.Internal;
import jp4js.nf2.rel.doc.Leaf;

public abstract class DocNodeBaseVisitor<T> {
    public T visit(DocNode node) {
        if (node instanceof Internal.PropertyNode) 
            return this.visitPropertyNode((Internal.PropertyNode)node);
        if (node instanceof Internal.IndexNode) 
            return this.visitIndexNode((Internal.IndexNode)node);
        if (node instanceof Leaf.StringNode) 
            return this.visitStringNode((Leaf.StringNode)node);
        if (node instanceof Leaf.DoubleNode) 
            return this.visitDoubleNode((Leaf.DoubleNode)node);
        if (node instanceof Leaf.IntNode)
            return this.visitIntNode((Leaf.IntNode)node);
        throw new IllegalArgumentException();
    }

    public T visitPropertyNode(Internal.PropertyNode node) {
        T ret = null;
        for (String fieldname: node) 
            ret = this.visit(node.get(fieldname));
        return ret;
    }

    public T visitIndexNode(Internal.IndexNode node) {
        T ret = null;
        for (DocNode indexNode: node)
            ret = this.visit(indexNode);
        return ret;
    }

    public T visitStringNode(Leaf.StringNode node) {
        return null;
    }

    public T visitDoubleNode(Leaf.DoubleNode node) {
        return null;
    }

    public T visitIntNode(Leaf.IntNode node) {
        return null;
    }

}
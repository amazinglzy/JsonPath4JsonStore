package jp4js.storage;

import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.nf2.rel.BasicType;
import jp4js.nf2.rel.doc.DocNode;
import jp4js.nf2.rel.doc.Internal;
import jp4js.nf2.rel.doc.Leaf;
import jp4js.nf2.rel.doc.NodeID;
import jp4js.utils.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;


public class MemStore implements Store {
    private HashMap<NodeID, DocNode> primaryIndex;
    private LinkedList<DocNode> docs;
    private int currentNodeId;

    public MemStore() {
        this.primaryIndex = new HashMap<>();
        this.docs = new LinkedList<>();
    }

    @Override
    public void add(Object json, Configuration configuration) {
        this.currentNodeId = 0;
        this.docs.add(
            this.add(json, configuration, this.docs.size())
        );
    }

    @Override
    public List<NodeID> docsID() {
        return new LinkedList<>() {{
            for (DocNode node: MemStore.this.docs)
                add(node.id());
        }};
    }

    @Override
    public DocNode doc(NodeID id) {
        return this.primaryIndex.get(id);
    }

    @Override
    public List<DocNode> docs() {
        return this.docs;
    }

    @Override
    public List<NodeID> children(NodeID id) {
        return new LinkedList<>() {{
            DocNode node = MemStore.this.primaryIndex.get(id);
            if (node != null) {
                if (node instanceof Internal.IndexNode) {
                    Internal.IndexNode indexNode = (Internal.IndexNode)node;
                    for (DocNode nestedNode: indexNode) 
                        this.add(nestedNode.id());
                }
                if (node instanceof Internal.PropertyNode) {
                    Internal.PropertyNode propertyNode = (Internal.PropertyNode)node;
                    for (String fieldname: propertyNode) 
                        this.add(propertyNode.get(fieldname).id());
                }
            } 
        }};
    }

    @Override
    public List<NodeID> children(NodeID id, List<String> properties) {
        return new LinkedList<>() {{
            DocNode node = MemStore.this.primaryIndex.get(id);
            if (node != null && node instanceof Internal.PropertyNode) {
                Internal.PropertyNode propertyNode = (Internal.PropertyNode)node;
                for (String fieldname: properties) 
                    if (propertyNode.contains(fieldname))
                        this.add(propertyNode.get(fieldname).id());
            }
        }};
    }

    @Override
    public List<NodeID> children(NodeID id, ArraySelections selections) {
        return new LinkedList<>() {{
            DocNode node = MemStore.this.primaryIndex.get(id);
            if (node != null && node instanceof Internal.IndexNode) {
                Internal.IndexNode indexNode = (Internal.IndexNode)node;
                for (Integer idx: selections.select()) 
                    if (indexNode.contains(idx)) this.add(indexNode.get(idx).id());
                    else break;
            }
        }};
    }

    @Override
    public List<NodeID> descendents(NodeID id, List<String> properties) {
        LinkedList<NodeID> ret = new LinkedList<>();
        DocNode node = this.primaryIndex.get(id);
        iterateNode(node, properties, ret);
        return ret;
    }

    private DocNode add(Object json, Configuration configuration, int docId) {
        NodeID id = new NodeID(docId, this.currentNodeId);
        this.currentNodeId ++;
        DocNode node;
        if (configuration.jsonProvider().isMap(json)) {
            node = new Internal.PropertyNodeBuilder() {{
                for (String fieldname: configuration.jsonProvider().getPropertyKeys(json)) {
                    put(
                        fieldname,
                        add(
                            configuration.jsonProvider().getMapValue(json, fieldname),
                            configuration,
                            docId
                        )
                    );
                }
            }}.build(id);
        } else if (configuration.jsonProvider().isArray(json)) {
            node = new Internal.IndexNodeBuilder() {{
                for (Object nestedJson: configuration.jsonProvider().toIterable(json)) {
                    add(MemStore.this.add(nestedJson, configuration, docId));
                }
            }}.build(id);
        } else {
            Object value = configuration.jsonProvider().unwrap(json);
            if (value instanceof Number) {
                if (value instanceof Double) {
                    node = Leaf.createDoubleNode(id, BasicType.createDDouble((Double)value));
                } else if (value instanceof Integer) {
                    node = Leaf.createIntNode(id, BasicType.createDInt((Integer)value));
                } else {
                    throw new IllegalArgumentException(value.toString());
                }
            } else if (value instanceof String) {
                node = Leaf.createStringNode(id, BasicType.createDString((String)value));
            } else if (value instanceof Boolean) {
                throw new IllegalArgumentException();
            } else {
                throw new IllegalArgumentException();
            }
        }

        this.primaryIndex.put(id, node);
        return node;
    }

    private void iterateNode(DocNode node, List<String> properties, List<NodeID> res) {
        if (node == null) 
            return;
        if (node instanceof Leaf.LeafNode) 
            return;
        if (node instanceof Internal.IndexNode) {
            Internal.IndexNode indexNode = (Internal.IndexNode)node;
            for (DocNode nestedNode: indexNode)
                iterateNode(nestedNode, properties, res);
        }

        if (node instanceof Internal.PropertyNode) {
            Internal.PropertyNode propertyNode = (Internal.PropertyNode)node;
            for (String fieldname: properties) 
                if (propertyNode.contains(fieldname))
                    res.add(propertyNode.get(fieldname).id());
            for (String fieldname: propertyNode) 
                iterateNode(propertyNode.get(fieldname), properties, res);
        }
    }
}
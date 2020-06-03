package jp4js.storage;

import jp4js.utils.Configuration;
import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.storage.doc.DocNode;
import jp4js.storage.doc.NodeID;

import java.util.List;


public interface Store {
    public void add(Object json, Configuration configuration);
    public List<NodeID> docsID();
    public DocNode doc(NodeID id);
    public List<DocNode> docs();
    public List<NodeID> children(NodeID id);
    public List<NodeID> children(NodeID id, List<String> properties);
    public List<NodeID> children(NodeID id, ArraySelections selections);
    public List<NodeID> descendents(NodeID id, List<String> properties);
}
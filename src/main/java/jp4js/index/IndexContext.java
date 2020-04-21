package jp4js.index;

import jp4js.index.node.CombinedNodeIterator;
import jp4js.index.node.Node;
import jp4js.index.node.NodeIterator;
import jp4js.index.node.SingleNodeIterator;
import jp4js.index.node.ArrayNode.ArraySelections;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IndexContext {
    private Map<String, LinkedList<Node>> objectsPartitions;
    private Map<Long, LinkedList<Node>> arraysPartitions;

    public IndexContext(Map<String, LinkedList<Node>> objectPartitions, Map<Long, LinkedList<Node>> arrayPartitions) {
        this.objectsPartitions = objectPartitions;
        this.arraysPartitions = arrayPartitions;
    }

    public NodeIterator openAll() {
        NodeIterator iter = null;
        for (String label: this.objectsPartitions.keySet()) {
            if (iter == null) iter = getObjectOrEmptyStream(label);
            else iter = new CombinedNodeIterator(iter, getObjectOrEmptyStream(label));
        }
        for (Long idx: this.arraysPartitions.keySet()) {
            if (iter == null) iter = getArrayOrEmptyStream(idx);
            else iter = new CombinedNodeIterator(iter, getArrayOrEmptyStream(idx));
        }
        return iter;
    }

    public NodeIterator openObject(String objectLabel) {
        return getObjectOrEmptyStream(objectLabel);
    }

    public NodeIterator openArray(Long arrayIndex) {
        return getArrayOrEmptyStream(arrayIndex);
    }

    public NodeIterator openObject(List<String> objectLabels) {
        if (objectLabels.size() == 1) return getObjectOrEmptyStream(objectLabels.get(0));
        NodeIterator ret = new CombinedNodeIterator(
                getObjectOrEmptyStream(objectLabels.get(0)),
                getObjectOrEmptyStream(objectLabels.get(1))
        );
        for (int i = 2; i < objectLabels.size(); i++) {
            ret = new CombinedNodeIterator(
                    getObjectOrEmptyStream(objectLabels.get(i)),
                    ret
            );
        }
        return ret;
    }

    public NodeIterator openArray(ArraySelections selections) {
        List<Integer> indicies = selections.select(this);
        if (indicies.size() == 0) return new SingleNodeIterator(new LinkedList<>());
        else {
            NodeIterator iter = null;
            for (Integer val: indicies) {
                if (iter == null) iter = getArrayOrEmptyStream(Long.valueOf(val));
                else iter = new CombinedNodeIterator(iter, getArrayOrEmptyStream(Long.valueOf(val)));
            }
            return iter;
        }
    }

    private NodeIterator getObjectOrEmptyStream(String objectLabel) {
        LinkedList<Node> ret = this.objectsPartitions.get(objectLabel);
        if (ret == null) ret = new LinkedList<Node>();
        return new SingleNodeIterator(ret);
    }

    private NodeIterator getArrayOrEmptyStream(Long arrayIndex) {
        LinkedList<Node> ret = this.arraysPartitions.get(arrayIndex);
        if (ret == null) ret = new LinkedList<Node>();
        return new SingleNodeIterator(ret);
    }
}

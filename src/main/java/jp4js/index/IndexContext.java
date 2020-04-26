package jp4js.index;

import jp4js.index.node.CombinedNodeIterator;
import jp4js.index.node.LabelNode;
import jp4js.utils.Iter;
import jp4js.index.node.SingleNodeIterator;
import jp4js.index.node.LabelArray.ArraySelections;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IndexContext {
    private Map<String, LinkedList<LabelNode>> objectsPartitions;
    private Map<Long, LinkedList<LabelNode>> arraysPartitions;

    public IndexContext(Map<String, LinkedList<LabelNode>> objectPartitions, Map<Long, LinkedList<LabelNode>> arrayPartitions) {
        this.objectsPartitions = objectPartitions;
        this.arraysPartitions = arrayPartitions;
    }

    public Iter<LabelNode> openAll() {
        Iter<LabelNode> iter = null;
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

    public Iter<LabelNode> openObject(String objectLabel) {
        return getObjectOrEmptyStream(objectLabel);
    }

    public Iter<LabelNode> openArray(Long arrayIndex) {
        return getArrayOrEmptyStream(arrayIndex);
    }

    public Iter<LabelNode> openObject(List<String> objectLabels) {
        if (objectLabels.size() == 1) return getObjectOrEmptyStream(objectLabels.get(0));
        Iter<LabelNode> ret = new CombinedNodeIterator(
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

    public Iter<LabelNode> openArray(ArraySelections selections) {
        List<Integer> indicies = selections.select();
        if (indicies.size() == 0) return new SingleNodeIterator(new LinkedList<>());
        else {
            Iter<LabelNode> iter = null;
            for (Integer val: indicies) {
                if (iter == null) iter = getArrayOrEmptyStream(Long.valueOf(val));
                else iter = new CombinedNodeIterator(iter, getArrayOrEmptyStream(Long.valueOf(val)));
            }
            return iter;
        }
    }

    private Iter<LabelNode> getObjectOrEmptyStream(String objectLabel) {
        LinkedList<LabelNode> ret = this.objectsPartitions.get(objectLabel);
        if (ret == null) ret = new LinkedList<LabelNode>();
        return new SingleNodeIterator(ret);
    }

    private Iter<LabelNode> getArrayOrEmptyStream(Long arrayIndex) {
        LinkedList<LabelNode> ret = this.arraysPartitions.get(arrayIndex);
        if (ret == null) ret = new LinkedList<LabelNode>();
        return new SingleNodeIterator(ret);
    }
}

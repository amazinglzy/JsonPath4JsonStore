package jp4js.index;

import jp4js.index.node.LabelNode;
import jp4js.utils.Configuration;
import jp4js.utils.iter.Iter;
import jp4js.utils.iter.MIter;
import jp4js.index.node.SingleNodeIterator;
import jp4js.index.node.LabelArray.ArraySelections;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IndexContext {
    private Map<String, LinkedList<LabelNode>> objectsPartitions;
    private Map<Long, LinkedList<LabelNode>> arraysPartitions;
    private Configuration configuration;

    public IndexContext(Map<String, LinkedList<LabelNode>> objectPartitions, Map<Long, LinkedList<LabelNode>> arrayPartitions, Configuration configuration){
        this.objectsPartitions = objectPartitions;
        this.arraysPartitions = arrayPartitions;
        this.configuration = configuration;
    }

    public Iter<LabelNode> openAll() {
        Iter<LabelNode> iter = null;
        for (String label: this.objectsPartitions.keySet()) {
            if (iter == null) iter = getObjectOrEmptyStream(label);
            else iter = new MIter<LabelNode>(iter, getObjectOrEmptyStream(label), LabelNode.comparator());
        }
        for (Long idx: this.arraysPartitions.keySet()) {
            if (iter == null) iter = getArrayOrEmptyStream(idx);
            else iter = new MIter<LabelNode>(iter, getArrayOrEmptyStream(idx), LabelNode.comparator());
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
        Iter<LabelNode> ret = new MIter<LabelNode>(
                getObjectOrEmptyStream(objectLabels.get(0)),
                getObjectOrEmptyStream(objectLabels.get(1)),
                LabelNode.comparator()
        );
        for (int i = 2; i < objectLabels.size(); i++) {
            ret = new MIter<LabelNode>(
                    getObjectOrEmptyStream(objectLabels.get(i)),
                    ret,
                    LabelNode.comparator()
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
                else iter = new MIter<LabelNode>(iter, getArrayOrEmptyStream(Long.valueOf(val)), LabelNode.comparator());
            }
            return iter;
        }
    }

    public Configuration configuration() {
        return this.configuration;
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

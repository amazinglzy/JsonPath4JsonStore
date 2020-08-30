package jp4js.storage;

import jp4js.utils.Configuration;
import jp4js.utils.nf2.Trans;
import jp4js.nf2.DType;
import jp4js.nf2.Scalar.DMapping;
import jp4js.nf2.Scalar.DList;
import jp4js.storage.node.IndexNode;
import jp4js.storage.node.NodeFactory;
import jp4js.storage.node.RepeatableNode;
import jp4js.storage.node.SingularNode;

import java.util.TreeMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Indexer {
    static class Timestamp {
        private long timestamp;

        public Timestamp() {
            this.timestamp = 0;
        }

        public Timestamp inc() {
            this.timestamp++;
            return this;
        }

        public long getTimestamp() {
            return this.timestamp;
        }
    }

    public static IndexContext index(Object json, Configuration configuration) {
        DType.Instance ins = Trans.fromJSON(json, configuration);
        return index(ins);
    }

    public static IndexContext index(DType.Instance ins) {
        ArrayList<SingularNode> allSingularNodes = new ArrayList<>();
        ArrayList<RepeatableNode> allRepeatableNodes = new ArrayList<>();
        TreeMap<String, LinkedList<SingularNode>> objectsPartitions = new TreeMap<>();
        TreeMap<String, TreeMap<Long, LinkedList<RepeatableNode>>> arraysPartitions = new TreeMap<>();
        IndexNode node = iterateJsonObject(
            "$", "$", ins, new Timestamp(), 0, allSingularNodes, allRepeatableNodes, 
            objectsPartitions, arraysPartitions);
        return new IndexContext(node, allSingularNodes, allRepeatableNodes, objectsPartitions, arraysPartitions);
    }

    private static IndexNode iterateJsonObject(
            String key, String path, DType.Instance mapping, Timestamp timestamp,
            int level, ArrayList<SingularNode> allSingularNodes, ArrayList<RepeatableNode> allRepeatableNodes,
            TreeMap<String, LinkedList<SingularNode>> objectsPartitions,
            TreeMap<String, TreeMap<Long, LinkedList<RepeatableNode>>> arraysPartitions) {
        SingularNode node = NodeFactory.create(key, -1, -1, level, mapping);
        node.first_visit = timestamp.getTimestamp();
        timestamp.inc();
        node.level = level;
        if (!objectsPartitions.containsKey(key))
            objectsPartitions.put(key, new LinkedList<SingularNode>());
        allSingularNodes.add(node);
        objectsPartitions.get(key).add(node);
        iterateJson(key, path, mapping, timestamp, level, allSingularNodes, allRepeatableNodes, objectsPartitions, arraysPartitions);
        node.last_visit = timestamp.getTimestamp();
        timestamp.inc();
        return node;
    }

    private static IndexNode iterateJsonArray(String key, long index, String path, DType.Instance ins, Timestamp timestamp,
            int level, ArrayList<SingularNode> allSingularNodes, ArrayList<RepeatableNode> allRepeatableNodes,
            TreeMap<String, LinkedList<SingularNode>> objectsPartitions,
            TreeMap<String, TreeMap<Long, LinkedList<RepeatableNode>>> arraysPartitions) {
        RepeatableNode node = NodeFactory.create(key, index, -1, -1, level, ins);
        node.first_visit = timestamp.getTimestamp();
        timestamp.inc();
        node.level = level;
        if (!arraysPartitions.containsKey(key)) {
            arraysPartitions.put(key, new TreeMap<>());
        }
        TreeMap<Long, LinkedList<RepeatableNode>> partition = arraysPartitions.get(key);
        if (!partition.containsKey(index)) {
            partition.put(index, new LinkedList<>());
        }
        partition.get(index).add(node);
        allRepeatableNodes.add(node);
        iterateJson(key, path, ins, timestamp, level, allSingularNodes, allRepeatableNodes, objectsPartitions, arraysPartitions);
        node.last_visit = timestamp.getTimestamp();
        timestamp.inc();
        return node;
    }

    private static void iterateJson(String key, String path, DType.Instance ins, Timestamp timestamp, int level,
        ArrayList<SingularNode> allSingularNodes, ArrayList<RepeatableNode> allRepeatableNodes,
        TreeMap<String, LinkedList<SingularNode>> objectsPartitions,
        TreeMap<String, TreeMap<Long, LinkedList<RepeatableNode>>> arraysPartitions) {
        if (ins instanceof DMapping.Instance) {
            List<String> properties = new ArrayList<String>() {
                {
                    for (String fieldname : (DMapping.Instance) ins) {
                        add(fieldname);
                    }
                }
            };
            properties.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            DMapping.Instance mapping = (DMapping.Instance) ins;
            for (String property : properties) {
                iterateJsonObject(property, path + "." + property, mapping.get(property), timestamp, level + 1,
                        allSingularNodes, allRepeatableNodes, objectsPartitions, arraysPartitions);
            }
        } else if (ins instanceof DList.Instance) {
            long index = 0;
            for (DType.Instance item : (DList.Instance) ins) {
                iterateJsonArray(key, index, path + "[" + String.valueOf(index) + "]", item, timestamp, level + 1,
                        allSingularNodes, allRepeatableNodes, objectsPartitions, arraysPartitions);
                index++;
            }
        }
    }
}

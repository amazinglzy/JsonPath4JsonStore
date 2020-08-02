package jp4js.storage;

import jp4js.utils.Configuration;
import jp4js.utils.nf2.Trans;
import jp4js.nf2.DType;
import jp4js.nf2.Scalar.DMapping;
import jp4js.nf2.Scalar.DList;
import jp4js.storage.node.LabelArray;
import jp4js.storage.node.LabelNode;
import jp4js.storage.node.LabelObject;

import java.util.*;

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
        Map<String, LinkedList<LabelNode>> objectsPartitions = new HashMap<String, LinkedList<LabelNode>>();
        Map<Long, LinkedList<LabelNode>> arraysPartitions = new HashMap<Long, LinkedList<LabelNode>>();
        LabelNode node = iterateJsonObject("$", "$", ins, new Timestamp(), 0, objectsPartitions, arraysPartitions);
        return new IndexContext(node, objectsPartitions, arraysPartitions);
    }

    private static LabelNode iterateJsonObject(String key, String path, DType.Instance mapping, Timestamp timestamp,
            int level, Map<String, LinkedList<LabelNode>> objectsPartitions,
            Map<Long, LinkedList<LabelNode>> arraysPartitions) {
        LabelObject node = new LabelObject(path, key, mapping);
        node.setFirstVisit(timestamp.getTimestamp());
        timestamp.inc();
        node.setLevel(level);
        if (!objectsPartitions.containsKey(key))
            objectsPartitions.put(key, new LinkedList<LabelNode>());
        objectsPartitions.get(key).add(node);
        iterateJson(path, mapping, timestamp, level, objectsPartitions, arraysPartitions);
        node.setLastVisit(timestamp.getTimestamp());
        timestamp.inc();
        return node;
    }

    private static LabelNode iterateJsonArray(long index, String path, DType.Instance ins, Timestamp timestamp,
            int level, Map<String, LinkedList<LabelNode>> objectsPartitions,
            Map<Long, LinkedList<LabelNode>> arraysPartitions) {
        LabelArray node = new LabelArray(path, index, ins);
        node.setFirstVisit(timestamp.getTimestamp());
        timestamp.inc();
        node.setLevel(level);
        if (!arraysPartitions.containsKey(index))
            arraysPartitions.put(index, new LinkedList<LabelNode>());
        arraysPartitions.get(index).add(node);
        iterateJson(path, ins, timestamp, level, objectsPartitions, arraysPartitions);
        node.setLastVisit(timestamp.getTimestamp());
        timestamp.inc();
        return node;
    }

    private static void iterateJson(String path, DType.Instance ins, Timestamp timestamp, int level,
            Map<String, LinkedList<LabelNode>> objectsPartitions, Map<Long, LinkedList<LabelNode>> arraysPartitions) {
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
                        objectsPartitions, arraysPartitions);
            }
        } else if (ins instanceof DList.Instance) {
            long index = 0;
            for (DType.Instance item : (DList.Instance) ins) {
                iterateJsonArray(index, path + "[" + String.valueOf(index) + "]", item, timestamp, level + 1,
                        objectsPartitions, arraysPartitions);
                index++;
            }
        }
    }
}

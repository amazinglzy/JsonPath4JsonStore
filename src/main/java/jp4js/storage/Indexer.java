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

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;

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
        ArrayList<IndexNode> allNodes = new ArrayList<>();
        HashMap<String, ArrayList<SingularNode>> singularNodes = new HashMap<>();
        ArrayList<ArrayList<SingularNode>> levelSingularNodes = new ArrayList<>();
        ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes = new ArrayList<>();
        IndexNode node = iterateJsonObject(
            "$", "$", ins, new Timestamp(), 0,
            allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes);
        return new IndexContext(node, allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes);
    }

    private static IndexNode iterateJsonObject(
            String key, String path, DType.Instance mapping, Timestamp timestamp,
            int level, ArrayList<IndexNode> allNodes, HashMap<String, ArrayList<SingularNode>> singularNodes,
            ArrayList<ArrayList<SingularNode>> levelSingularNodes,
            ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes) {
        SingularNode node = NodeFactory.create(key, -1, -1, level, mapping);
        node.first_visit = timestamp.getTimestamp();
        timestamp.inc();
        node.level = level;

        if (!singularNodes.containsKey(key))
            singularNodes.put(key, new ArrayList<SingularNode>());
        singularNodes.get(key).add(node);
        while (level >= levelSingularNodes.size()) 
            levelSingularNodes.add(new ArrayList<>());
        levelSingularNodes.get(level).add(node);
        allNodes.add(node);

        iterateJson(key, path, mapping, timestamp, level,
            allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes);
        node.last_visit = timestamp.getTimestamp();
        timestamp.inc();
        return node;
    }

    private static IndexNode iterateJsonArray(String key, long index, String path, DType.Instance ins, Timestamp timestamp,
            int level, 
            ArrayList<IndexNode> allNodes, HashMap<String, ArrayList<SingularNode>> singularNodes,
            ArrayList<ArrayList<SingularNode>> levelSingularNodes,
            ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes) {
        RepeatableNode node = NodeFactory.create(key, index, -1, -1, level, ins);
        node.first_visit = timestamp.getTimestamp();
        timestamp.inc();
        node.level = level;

        while (level >= levelRepeatableNodes.size()) 
            levelRepeatableNodes.add(new ArrayList<>());
        levelRepeatableNodes.get(level).add(node);
        allNodes.add(node);

        iterateJson(key, path, ins, timestamp, level, allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes);
        node.last_visit = timestamp.getTimestamp();
        timestamp.inc();
        return node;
    }

    private static void iterateJson(String key, String path, DType.Instance ins, Timestamp timestamp, int level,
            ArrayList<IndexNode> allNodes, HashMap<String, ArrayList<SingularNode>> singularNodes,
            ArrayList<ArrayList<SingularNode>> levelSingularNodes,
            ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes) {
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
                    allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes);
            }
        } else if (ins instanceof DList.Instance) {
            long index = 0;
            for (DType.Instance item : (DList.Instance) ins) {
                iterateJsonArray(key, index, path + "[" + String.valueOf(index) + "]", item, timestamp, level + 1,
                    allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes);
                index++;
            }
        }
    }
}

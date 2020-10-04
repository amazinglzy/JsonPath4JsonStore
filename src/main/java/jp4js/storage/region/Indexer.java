package jp4js.storage.region;

import jp4js.utils.Configuration;
import jp4js.utils.nf2.Trans;
import jp4js.nf2.DType;
import jp4js.nf2.Scalar.DMapping;
import jp4js.nf2.Scalar.DList;
import jp4js.storage.region.node.IndexNode;
import jp4js.storage.region.node.NodeFactory;
import jp4js.storage.region.node.RepeatableNode;
import jp4js.storage.region.node.SingularNode;

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
        ArrayList<ArrayList<IndexNode>> levelAllNodes = new ArrayList<>();
        HashMap<String, ArrayList<ArrayList<SingularNode>>> levelTagSingularNodes = new HashMap<>();
        IndexNode node = iterateJsonObject(
            "$", "$", ins, new Timestamp(), 0,
            allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes, levelAllNodes, levelTagSingularNodes);
        return new IndexContext(node, allNodes, singularNodes,
            levelSingularNodes, levelRepeatableNodes, levelAllNodes, levelTagSingularNodes);
    }

    private static IndexNode iterateJsonObject(
            String key, String path, DType.Instance mapping, Timestamp timestamp,
            int level, ArrayList<IndexNode> allNodes, HashMap<String, ArrayList<SingularNode>> singularNodes,
            ArrayList<ArrayList<SingularNode>> levelSingularNodes,
            ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes,
            ArrayList<ArrayList<IndexNode>> levelAllNodes,
            HashMap<String, ArrayList<ArrayList<SingularNode>>> levelTagSingularNodes) {
        SingularNode node = NodeFactory.create(key, -1, -1, level, mapping);
        node.first_visit = timestamp.getTimestamp();
        timestamp.inc();
        node.level = level;

        // singularNodes
        if (!singularNodes.containsKey(key))
            singularNodes.put(key, new ArrayList<SingularNode>());
        singularNodes.get(key).add(node);
        // levelTagSingularNodes
        if (!levelTagSingularNodes.containsKey(key))
            levelTagSingularNodes.put(key, new ArrayList<>());
        ArrayList<ArrayList<SingularNode>> currentLevelNodes = levelTagSingularNodes.get(key);
        while (level >= currentLevelNodes.size()) 
            currentLevelNodes.add(new ArrayList<>());
        currentLevelNodes.get(level).add(node);
        // levelSingularNodes
        while (level >= levelSingularNodes.size()) 
            levelSingularNodes.add(new ArrayList<>());
        levelSingularNodes.get(level).add(node);
        // levelAllNodes
        while (level >= levelAllNodes.size())
            levelAllNodes.add(new ArrayList<>());
        levelAllNodes.get(level).add(node);
        // allNodes
        allNodes.add(node);

        iterateJson(key, path, mapping, timestamp, level,
            allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes,
            levelAllNodes, levelTagSingularNodes);
        node.last_visit = timestamp.getTimestamp();
        timestamp.inc();
        return node;
    }

    private static IndexNode iterateJsonArray(String key, long index, String path, DType.Instance ins, Timestamp timestamp,
            int level, 
            ArrayList<IndexNode> allNodes, HashMap<String, ArrayList<SingularNode>> singularNodes,
            ArrayList<ArrayList<SingularNode>> levelSingularNodes,
            ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes,
            ArrayList<ArrayList<IndexNode>> levelAllNodes,
            HashMap<String, ArrayList<ArrayList<SingularNode>>> levelTagSingularNodes) {
        RepeatableNode node = NodeFactory.create(key, index, -1, -1, level, ins);
        node.first_visit = timestamp.getTimestamp();
        timestamp.inc();
        node.level = level;

        while (level >= levelRepeatableNodes.size()) 
            levelRepeatableNodes.add(new ArrayList<>());
        levelRepeatableNodes.get(level).add(node);
        while (level >= levelAllNodes.size()) 
            levelAllNodes.add(new ArrayList<>());
        levelAllNodes.get(level).add(node);
        allNodes.add(node);

        iterateJson(key, path, ins, timestamp, level, allNodes, singularNodes,
            levelSingularNodes, levelRepeatableNodes, levelAllNodes, levelTagSingularNodes);
        node.last_visit = timestamp.getTimestamp();
        timestamp.inc();
        return node;
    }

    private static void iterateJson(String key, String path, DType.Instance ins, Timestamp timestamp, int level,
            ArrayList<IndexNode> allNodes, HashMap<String, ArrayList<SingularNode>> singularNodes,
            ArrayList<ArrayList<SingularNode>> levelSingularNodes,
            ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes,
            ArrayList<ArrayList<IndexNode>> levelAllNodes,
            HashMap<String, ArrayList<ArrayList<SingularNode>>> levelTagSingularNodes) {
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
                    allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes, levelAllNodes, levelTagSingularNodes);
            }
        } else if (ins instanceof DList.Instance) {
            long index = 0;
            for (DType.Instance item : (DList.Instance) ins) {
                iterateJsonArray(key, index, path + "[" + String.valueOf(index) + "]", item, timestamp, level + 1,
                    allNodes, singularNodes, levelSingularNodes, levelRepeatableNodes, levelAllNodes, levelTagSingularNodes);
                index++;
            }
        }
    }
}

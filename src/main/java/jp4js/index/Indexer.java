package jp4js.index;

import jp4js.utils.Configuration;
import jp4js.index.node.LabelArray;
import jp4js.index.node.LabelNode;
import jp4js.index.node.LabelObject;

import java.util.*;

public class Indexer {
    static class Timestamp {
        private long timestamp;
        public Timestamp() {this.timestamp = 0;}

        public Timestamp inc() {
            this.timestamp ++;
            return this;
        }
        public long getTimestamp() {
            return this.timestamp;
        }
    }

    public static IndexContext index(Object json, Configuration configuration) {
        Map<String, LinkedList<LabelNode>> objectsPartitions = new HashMap<String, LinkedList<LabelNode>>();
        Map<Long, LinkedList<LabelNode>> arraysPartitions = new HashMap<Long, LinkedList<LabelNode>>();
        iterateJsonObject("$", json, json, configuration, new Timestamp(), 0, objectsPartitions, arraysPartitions);
        return new IndexContext(objectsPartitions, arraysPartitions);
    }

    private static void iterateJsonObject(String key,
                                          Object json,
                                          Object rootDocument,
                                          Configuration configuration,
                                          Timestamp timestamp,
                                          int level,
                                          Map<String, LinkedList<LabelNode>> objectsPartitions,
                                          Map<Long, LinkedList<LabelNode>> arraysPartitions) {
        LabelObject node = new LabelObject(key, json, rootDocument);
        node.setFirstVisit(timestamp.getTimestamp()); timestamp.inc();
        node.setLevel(level);
        if (!objectsPartitions.containsKey(key)) objectsPartitions.put(key, new LinkedList<LabelNode>());
        objectsPartitions.get(key).add(node);
        iterateJson(json, rootDocument, configuration, timestamp, level, objectsPartitions, arraysPartitions);
        node.setLastVisit(timestamp.getTimestamp()); timestamp.inc();
    }

    private static void iterateJsonArray(long index,
                                         Object json,
                                         Object rootDocument,
                                         Configuration configuration,
                                         Timestamp timestamp,
                                         int level,
                                         Map<String, LinkedList<LabelNode>> objectsPartitions,
                                         Map<Long, LinkedList<LabelNode>> arraysPartitions) {
        LabelArray node = new LabelArray(index, json, rootDocument);
        node.setFirstVisit(timestamp.getTimestamp()); timestamp.inc();
        node.setLevel(level);
        if (!arraysPartitions.containsKey(index)) arraysPartitions.put(index, new LinkedList<LabelNode>());
        arraysPartitions.get(index).add(node);
        iterateJson(json, rootDocument, configuration, timestamp, level, objectsPartitions, arraysPartitions);
        node.setLastVisit(timestamp.getTimestamp()); timestamp.inc();
    }

    private static void iterateJson(
            Object json,
            Object rootDocument,
            Configuration configuration,
            Timestamp timestamp,
            int level,
            Map<String, LinkedList<LabelNode>> objectsPartitions,
            Map<Long, LinkedList<LabelNode>> arraysPartitions) {
        if (configuration.jsonProvider().isMap(json)) {
            List<String> properties = new ArrayList<String>(configuration.jsonProvider().getPropertyKeys(json));
            properties.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            for (String property: properties) {
                iterateJsonObject(
                        property,
                        configuration.jsonProvider().getMapValue(json, property),
                        rootDocument,
                        configuration,
                        timestamp,
                        level + 1,
                        objectsPartitions,
                        arraysPartitions);
            }
        } else if (configuration.jsonProvider().isArray(json)) {
            long index = 0;
            for (Object obj: configuration.jsonProvider().toIterable(json)) {
                iterateJsonArray(
                        index,
                        obj,
                        rootDocument,
                        configuration,
                        timestamp,
                        level + 1,
                        objectsPartitions,
                        arraysPartitions);
                index ++;
            }
        }
    }
}

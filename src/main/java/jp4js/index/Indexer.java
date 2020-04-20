package jp4js.index;

import jp4js.utils.Configuration;
import jp4js.index.node.ArrayNode;
import jp4js.index.node.Node;
import jp4js.index.node.ObjectNode;

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
        Map<String, LinkedList<Node>> objectsPartitions = new HashMap<String, LinkedList<Node>>();
        Map<Long, LinkedList<Node>> arraysPartitions = new HashMap<Long, LinkedList<Node>>();
        iterateJsonObject("$", json, json, configuration, new Timestamp(), 0, objectsPartitions, arraysPartitions);
        return new IndexContext(objectsPartitions, arraysPartitions);
    }

    private static void iterateJsonObject(String key,
                                          Object json,
                                          Object rootDocument,
                                          Configuration configuration,
                                          Timestamp timestamp,
                                          int level,
                                          Map<String, LinkedList<Node>> objectsPartitions,
                                          Map<Long, LinkedList<Node>> arraysPartitions) {
        ObjectNode node = new ObjectNode(key, json, rootDocument);
        node.setFirstVisit(timestamp.getTimestamp()); timestamp.inc();
        node.setLevel(level);
        if (!objectsPartitions.containsKey(key)) objectsPartitions.put(key, new LinkedList<Node>());
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
                                         Map<String, LinkedList<Node>> objectsPartitions,
                                         Map<Long, LinkedList<Node>> arraysPartitions) {
        ArrayNode node = new ArrayNode(index, json, rootDocument);
        node.setFirstVisit(timestamp.getTimestamp()); timestamp.inc();
        node.setLevel(level);
        if (!arraysPartitions.containsKey(index)) arraysPartitions.put(index, new LinkedList<Node>());
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
            Map<String, LinkedList<Node>> objectsPartitions,
            Map<Long, LinkedList<Node>> arraysPartitions) {
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

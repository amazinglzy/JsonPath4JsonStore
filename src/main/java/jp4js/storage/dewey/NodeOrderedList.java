package jp4js.storage.dewey;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import jp4js.algebra.tpl.DBody;
import jp4js.algebra.tpl.ListTuple;
import jp4js.algebra.tpl.Tuple;
import jp4js.utils.iter.Iter;

public class NodeOrderedList implements Iterable<IndexNode> {
    public List<IndexNode> nodes;
    public int level;
    private Comparator<IndexNode> cmp;

    public NodeOrderedList(int level)  {
        this.level = level;
        this.cmp = IndexNode.comparator(level);
        this.nodes = null;
    }

    public NodeOrderedList(int level, Iter<IndexNode> iter) {
        this.level = level;
        this.cmp = IndexNode.comparator(level);
        this.nodes = new LinkedList<>() {{
            while (iter.valid()) {
                IndexNode node = iter.read();
                add(node);
                iter.next();
            }
        }};
    }

    public NodeOrderedList(int level, NodeOrderedList lst) {
        this.level = level;
        this.cmp = IndexNode.comparator(level);
        this.nodes = lst.nodes;
    }

    public void addAll(NodeOrderedList other) {
        if (other == null) {
            return;
        }

        if (other.nodes == null) {
            return;
        }

        if (this.nodes == null) {
            this.nodes = new LinkedList<>() {{
                for (IndexNode node: other.nodes) {
                    add(new IndexNode(node.indexes, node.data));
                }
            }};
            return;
        }

        LinkedList<IndexNode> replace = new LinkedList<>();
        ListIterator<IndexNode> baseIter = this.nodes.listIterator();
        ListIterator<IndexNode> addIter = other.nodes.listIterator();

        while (baseIter.hasNext() && addIter.hasNext()) {
            IndexNode first = baseIter.next();
            IndexNode second = addIter.next();
            int r = cmp.compare(first, second);
            if (r <= 0) {
                replace.add(first);
                addIter.previous();
            } else {
                replace.add(second);
                baseIter.previous();
            }
        }

        while (baseIter.hasNext()) {
            replace.add(baseIter.next());
        }

        while (addIter.hasNext()) {
            replace.add(addIter.next());
        }

        this.nodes = replace;
    }

    public void crossProduct(NodeOrderedList nested) {
        if (nested == null || nested.nodes == null) {
            return;
        }
        if (this.nodes == null) {
            this.nodes = new LinkedList<>();
            for (IndexNode node: nested) {
                Tuple t = new Tuple(1);
                t.put(0, node.data);
                this.nodes.add(new IndexNode(node.indexes, t));
            }
        } else {
            LinkedList<IndexNode> other = new LinkedList<>();

            ListIterator<IndexNode> baseIter = this.nodes.listIterator();
            ListIterator<IndexNode> addIter = nested.nodes.listIterator();

            ArrayList<IndexNode> baseBuffer = new ArrayList<>();
            ArrayList<IndexNode> addBuffer = new ArrayList<>();
            while (baseIter.hasNext() && addIter.hasNext()) {

                if (baseBuffer.isEmpty()) {
                    IndexNode firstNode = baseIter.next();
                    baseBuffer.add(firstNode);
                    while (baseIter.hasNext()) {
                        IndexNode node = baseIter.next();
                        if (this.cmp.compare(firstNode, node) == 0) {
                            baseBuffer.add(node);
                        } else {
                            baseIter.previous();
                            break;
                        }
                    }
                }

                if (addBuffer.isEmpty()) {
                    IndexNode firstNode = addIter.next();
                    addBuffer.add(firstNode);
                    while (addIter.hasNext()) {
                        IndexNode node = addIter.next();
                        if (this.cmp.compare(firstNode, node) == 0) {
                            addBuffer.add(node);
                        } else {
                            addIter.previous();
                            break;
                        }
                    }
                }

                int res = this.cmp.compare(baseBuffer.get(0), addBuffer.get(0));
                if (res < 0) {
                    baseBuffer.clear();
                } else if (res > 0) {
                    addBuffer.clear();
                } else {
                    for (IndexNode baseNode: baseBuffer) {
                        Tuple baseTuple = (Tuple)baseNode.data;
                        for (IndexNode addNode: addBuffer) {
                            Tuple t = new Tuple(baseTuple.size() + 1);
                            for (int i = 0; i < baseTuple.size(); ++ i) {
                                t.put(i, baseTuple.get(i));
                            }
                            t.put(baseTuple.size(), addNode.data);
                            other.add(new IndexNode(baseNode.indexes, t));
                        }
                    }
                    baseBuffer.clear();
                    addBuffer.clear();
                }
            }

            this.nodes = other;
        }
    }

    public void shrink() {
        if (this.nodes == null) {
            return;
        }
        LinkedList<IndexNode> replace = new LinkedList<>();
        ListIterator<IndexNode> iter = this.nodes.listIterator();
        while (iter.hasNext()) {
            IndexNode first = iter.next();
            List<DBody> data = new LinkedList<DBody>() {{ add(first.data); }};
            while (iter.hasNext()) {
                IndexNode next = iter.next();
                if (this.cmp.compare(first, next) == 0) {
                    data.add(next.data);
                } else {
                    iter.previous();
                    break;
                }
            }

            replace.add(
                new IndexNode(
                    first.indexes,
                    new ListTuple(data)
                )
            );
        }

        this.nodes = replace;
    }


    @Override
    public Iterator<IndexNode> iterator() {
        if (this.nodes != null) {
            return this.nodes.iterator();
        } else {
            return new LinkedList<IndexNode>().iterator();
        }
    }
}

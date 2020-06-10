package jp4js.query.baseline;

import jp4js.nf2.rel.DType;
import jp4js.nf2.rel.NestedRelation;
import jp4js.nf2.rel.NestedRelationBuilder;
import jp4js.query.path.ArrayNode;
import jp4js.query.path.DecendentNode;
import jp4js.query.path.Path;
import jp4js.query.path.PathNode;
import jp4js.query.path.PropertyNode;
import jp4js.query.path.RootNode;
import jp4js.query.path.WildcardNode;
import jp4js.storage.Store;
import jp4js.storage.doc.DocNode;
import jp4js.storage.doc.NodeID;
import jp4js.utils.Utils;
import jp4js.utils.nf2.Json2DType;
import jp4js.utils.query.PathBaseListener;
import jp4js.utils.query.PathListener;

import java.util.List;

import com.ibm.icu.impl.UResource.Array;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class DocScan {
    private Store store;
    private Path path;

    public DocScan(Store store, Path path) {
        this.store = store;
        this.path = path;
    }

    public static class Iter {
        private Store store;
        private Path path;
        private List<DocNode[]> nodeMatches;
        private NestedRelation relation;
        private NestedRelation.Instance instance;
        private int currentColumnIndex;

        public Iter(Store store, Path path) {
            this.store = store;
            this.path = path;
            this.nodeMatches = this.iterateToFindMatches(this.store.docsID(), path.root());
            this.relation = new RelationConstruct(path, nodeMatches) {{ iterate(); }}.relation();
            // this.instance = this.constructRelationInstance();
        }

        private List<DocNode[]> iterateToFindMatches(List<NodeID> currentMatches, PathNode node) {
            List<DocNode[]> ret = new LinkedList<>();
            if (node.children().size() == 0) {
                for (NodeID match: currentMatches) 
                    ret.add(new DocNode[]{ this.store.doc(match) });
            } else {
                for (NodeID match: currentMatches) {
                    List<DocNode[]> results = new LinkedList<DocNode[]>() {{
                        push(new DocNode[]{});
                    }};
                    for (PathNode child: node.children()) {
                        List<DocNode[]> childResults;
                        if (child instanceof PropertyNode) 
                            childResults = iterateToFindMatches(
                                this.store.children(match, ((PropertyNode)child).properties()), 
                                child
                            );
                        else if (child instanceof ArrayNode) 
                            childResults = iterateToFindMatches(
                                this.store.children(match, ((ArrayNode)child).selections()), 
                                child
                            );
                        else if (child instanceof WildcardNode) 
                            childResults = iterateToFindMatches(
                                this.store.children(match), 
                                child
                            );
                        else if (child instanceof DecendentNode) 
                            childResults = iterateToFindMatches(
                                this.store.descendents(match, ((DecendentNode)child).properties()),
                                child
                            );
                        else
                            throw new IllegalArgumentException();
                        
                        List<DocNode[]> newResults = new LinkedList<DocNode[]>();
                        for (DocNode[] result: results) {
                            for (DocNode[] childResult: childResults) {
                                DocNode[] newResult = new DocNode[result.length + childResult.length];
                                for (int i = 0; i < result.length; i++) 
                                    newResult[i] = result[i];
                                for (int i = 0; i < childResult.length; i++)
                                    newResult[result.length + i] = childResult[i];
                                newResults.add(newResult);
                            }
                        }

                        results = newResults;
                    }
                    ret.addAll(results);
                }
            }
            return ret;
        }
    }

    public static class InstanceConstruct {
        private Path path;
        private List<DocNode[]> nodeMatches;
        private NestedRelation.InstanceBuilder builder;
        private int currentRowIndex;
        private int currentColumnIndex;

        public InstanceConstruct(Path path, List<DocNode[]> nodeMatches, NestedRelation relation) {
            this.path = path;
            this.nodeMatches = nodeMatches;
            this.builder = relation.builder();
        }

        public NestedRelation.Instance instance() {
            TupleConstruct constructor = new TupleConstruct(path, this.builder);
            this.currentRowIndex = 0;
            while (this.currentRowIndex < this.nodeMatches.size()) {
                this.currentColumnIndex = 0;
                this.builder.begin();
                constructor.iterate();
                this.builder.end();
                this.currentRowIndex ++;
            }
            return this.builder.build();
        }

        public class TupleConstruct extends PathListener {
            private NestedRelation.InstanceBuilder builder;

            public TupleConstruct(Path path, NestedRelation.InstanceBuilder builder) {
                super(path);
                this.builder = builder;
            }


            @Override
            public void enterArrayNode(ArrayNode node) {
                super.enterArrayNode(node);
                this.builder.enter(this.parentFieldname());
            }

            @Override
            public void exitArrayNode(ArrayNode node) {
                super.exitArrayNode(node);
                this.builder.exit();
            }

            public void visitLeafRootNode(RootNode node) {
                this.collect();
            }

            public void visitLeafPropertyNode(PropertyNode node) {
                this.collect();
            }

            public void visitLeafArrayNode(ArrayNode node) {
                this.collect();
            }

            public void visitLeafWildcardNode(WildcardNode node) {
                this.collect();
            }

            public void visitLeafDecendentNode(DecendentNode node) {
                this.collect();
            }

            private void collect() {
                List<DocNode> nodes = new LinkedList<>() {{
                    for (DocNode[] nodeMatch: InstanceConstruct.this.nodeMatches) 
                        add(nodeMatch[InstanceConstruct.this.currentColumnIndex]);
                }};
                builder.put(this.currentFieldname(), new Json2DType(nodes).relation());
                InstanceConstruct.this.currentColumnIndex ++;
            }
        }
    }

    public static class RelationConstruct extends PathListener {
        private NestedRelationBuilder builder;
        private List<DocNode[]> nodeMatches;
        private int currentColumnIndex;

        public RelationConstruct(Path path, List<DocNode[]> nodeMatches) {
            super(path);
            this.nodeMatches = nodeMatches;
            this.builder = new NestedRelationBuilder();
            this.currentColumnIndex = 0;
        }

        public NestedRelation relation() {
            return this.builder.build();
        }

        @Override
        public void enterArrayNode(ArrayNode node) {
            super.enterArrayNode(node);
            this.builder.enter(this.parentFieldname());
        }

        @Override
        public void exitArrayNode(ArrayNode node) {
            super.exitArrayNode(node);
            this.builder.exit();
        }

        public void visitLeafRootNode(RootNode node) {
            this.collect();
        }

        public void visitLeafPropertyNode(PropertyNode node) {
            this.collect();
        }

        public void visitLeafArrayNode(ArrayNode node) {
            this.collect();
        }

        public void visitLeafWildcardNode(WildcardNode node) {
            this.collect();
        }

        public void visitLeafDecendentNode(DecendentNode node) {
            this.collect();
        }

        private void collect() {
            List<DocNode> nodes = new LinkedList<>() {{
                for (DocNode[] nodeMatch: RelationConstruct.this.nodeMatches) 
                    add(nodeMatch[RelationConstruct.this.currentColumnIndex]);
            }};
            builder.put(this.currentFieldname(), new Json2DType(nodes).relation());
            this.currentColumnIndex ++;
        }
    }
}
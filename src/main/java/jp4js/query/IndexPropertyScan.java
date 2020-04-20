package jp4js.query;

import jp4js.index.IndexContext;
import jp4js.index.node.Node;
import jp4js.index.node.NodeIterator;
import jp4js.query.PlanOperator;

import java.util.LinkedList;
import java.util.List;

public class IndexPropertyScan implements PlanOperator {
    private List<String> properties;
    private IndexContext indexContext;

    public IndexPropertyScan(IndexContext indexContext, List<String> properties) {
        this.properties = properties;
        this.indexContext = indexContext;
    }

    public IndexPropertyScan(IndexContext indexContext, final String property) {
        this.properties = new LinkedList<String>(){{
            add(property);
        }};
        this.indexContext = indexContext;
    }

    @Override
    public NodeIterator iterator() {
        return this.indexContext.openObject(properties);
    }
}

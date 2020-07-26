package jp4js.query;

import jp4js.utils.iter.Iter;
import jp4js.storage.IndexContext;
import jp4js.storage.node.LabelNode;

import java.util.LinkedList;
import java.util.List;

public class IndexPropertyScan implements PlanOperator<LabelNode> {
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
    public Iter<LabelNode> iterator() {
        return this.indexContext.openObject(properties);
    }
}

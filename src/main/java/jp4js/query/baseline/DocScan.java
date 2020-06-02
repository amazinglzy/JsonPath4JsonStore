package jp4js.query.baseline;

import jp4js.storage.Store;
import jp4js.query.path.Path;

public class DocScan {
    private Store store;
    private Path path;

    public DocScan(Store store, Path path) {
        this.store = store;
        this.path = path;
    }

    /*
    Implementation
    NestedRelation.Instance instance = rel.instanceBuilder()
    List<Object[]> matches;
    createRelationByMachesAndPath()
    matches transformed into NestedRelation.Instance

    createNestedRelation by List<Object>

    how to create matches ? how to define dfs ?
    public List<Object[]> dfs(List<Node> matches, PathNode node) {
        List<Object[]> ret = new LinkedList<>();
        for (Node match: matches) {
            List<Object[]> results;
            for (PathNode child: node.child) {
                List<Node> childMatches = find matches of child by match
                results = results.catasian(dfs(childMatches, child))
            }
            ret.addAll(results);
        }
    }
    */
}
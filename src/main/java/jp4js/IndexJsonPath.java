package jp4js;

import java.util.List;

import jp4js.algebra.Match;
import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.tpl.DBody;
import jp4js.storage.region.IndexContext;
import jp4js.storage.region.Indexer;
import jp4js.storage.region.RegionScan;
import jp4js.utils.Configuration;
import jp4js.utils.query.PathCompiler;

public class IndexJsonPath implements JsonPath {
    private static Configuration configuration = Configuration.defaultConfiguration();
    private IndexContext indexContext;

    public IndexJsonPath() {}

    @Override
    public void index(String json) {
        this.indexContext = Indexer.index(
            IndexJsonPath.configuration.jsonProvider().parse(json),
            IndexJsonPath.configuration);
    }
    
    @Override
    public List<DBody> query(String query) {
        StructureList lst = PathCompiler.fromString(query);
        RegionScan split = new RegionScan(this.indexContext, lst);
        try {
            Match match = split.open();
            // assert(match.isValid());
            return match.body();
        } catch (RegionScan.MatchException e) {
            e.printStackTrace();
        }
        return null;
    }
}
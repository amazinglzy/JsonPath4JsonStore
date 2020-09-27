package jp4js;

import java.util.List;

import jp4js.nf2.Match;
import jp4js.nf2.op.SSplit;
import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.tpl.DBody;
import jp4js.storage.IndexContext;
import jp4js.storage.Indexer;
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
        SSplit split = new SSplit(this.indexContext, lst);
        try {
            Match match = split.open();
            // assert(match.isValid());
            return match.body();
        } catch (SSplit.MatchException e) {
            e.printStackTrace();
        }
        return null;
    }
}
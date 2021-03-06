package jp4js;

import java.util.List;

import jp4js.algebra.DType;
import jp4js.algebra.Match;
import jp4js.algebra.op.FullScan;
import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.tpl.DBody;
import jp4js.utils.Configuration;
import jp4js.utils.algebra.Trans;
import jp4js.utils.query.PathCompiler;

public class NaiveJsonPath implements JsonPath {
    private static Configuration configuration = Configuration.defaultConfiguration();
    private DType.Instance instance;

    public NaiveJsonPath() {}

    @Override
    public void index(String json) {
        this.instance = Trans.fromJSON(
            NaiveJsonPath.configuration.jsonProvider().parse(json),
            NaiveJsonPath.configuration);
    }

    @Override
    public List<DBody> query(String query) {
        StructureList lst = PathCompiler.fromString(query);
        FullScan split = new FullScan(this.instance, lst);
        try {
            Match match = split.open();
            // assert(match.isValid());
            return match.body();
        } catch (FullScan.MatchException e) {
            e.printStackTrace();
        }
        return null;
    }
}
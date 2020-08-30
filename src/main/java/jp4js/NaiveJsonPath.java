package jp4js;

import jp4js.nf2.DType;
import jp4js.nf2.Match;
import jp4js.nf2.op.Split;
import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.tpl.DBody;
import jp4js.utils.Configuration;
import jp4js.utils.nf2.Trans;
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
    public DBody query(String query) {
        StructureList lst = PathCompiler.fromString(query);
        Split split = new Split(this.instance, lst);
        try {
            Match match = split.open();
            // assert(match.isValid());
            return match.body();
        } catch (Split.MatchException e) {
            e.printStackTrace();
        }
        return null;
    }
}
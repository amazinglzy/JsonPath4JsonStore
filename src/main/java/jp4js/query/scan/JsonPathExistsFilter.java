package jp4js.query.scan;

import jp4js.utils.filter.Filter;
import jp4js.utils.Configuration;
import jp4js.utils.Value;

import org.antlr.v4.runtime.tree.*;


public class JsonPathExistsFilter<E extends Value> implements Filter<E> {
    private ParseTree tree;
    private Configuration configuration;

    public JsonPathExistsFilter(ParseTree tree, Configuration configuration) {
        this.tree = tree;
        this.configuration = configuration;
    }

    @Override
    public boolean accept(E record)  {
        RelativeJsonPathScan scan = new RelativeJsonPathScan(record.getValue(), configuration);
        scan.visit(this.tree);
        return scan.exists();
    }
}
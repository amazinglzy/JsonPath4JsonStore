package jp4js.query.join;

import jp4js.utils.filter.Filter;
import jp4js.utils.Configuration;
import jp4js.query.scan.RelativeJsonPathScan;

import org.antlr.v4.runtime.tree.*;

public class ExistsFilter implements Filter<Item> {
    private ParseTree tree;
    private Configuration configuration;

    public ExistsFilter(ParseTree tree, Configuration configuration) {
        this.tree = tree;
        this.configuration = configuration;
    }

    @Override
    public boolean accept(Item item)  {
        RelativeJsonPathScan scan = new RelativeJsonPathScan(item.getData().getPath(), item.getData().getValue(), configuration);
        scan.visit(this.tree);
        return scan.exists();
    }
}
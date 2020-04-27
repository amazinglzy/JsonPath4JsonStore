package jp4js.query.scan;

import jp4js.utils.filter.Filter;
import jp4js.utils.Configuration;
import jp4js.query.RecordSet.Record;

import org.antlr.v4.runtime.tree.*;


public class JsonPathExistsFilter implements Filter<Record> {
    private ParseTree tree;
    private Configuration configuration;

    public JsonPathExistsFilter(ParseTree tree, Configuration configuration) {
        this.tree = tree;
        this.configuration = configuration;
    }

    @Override
    public boolean accept(Record record)  {
        RelativeJsonPathScan scan = new RelativeJsonPathScan(record.getPath(), record.getValue(), configuration);
        scan.visit(this.tree);
        return scan.exists();
    }
}
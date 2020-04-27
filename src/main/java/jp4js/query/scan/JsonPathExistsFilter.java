package jp4js.query.scan;

import jp4js.utils.filter.Filter;
import jp4js.utils.Configuration;
import jp4js.query.RecordSet.Record;

import org.antlr.v4.runtime.tree.*;


public class JsonPathExistsFilter implements Filter<Record> {
    private ParseTree tree;
    private Configuration configuration;
    private ParseTreeWalker walker = new ParseTreeWalker();

    public JsonPathExistsFilter(ParseTree tree, Configuration configuration) {
        this.tree = tree;
    }

    @Override
    public boolean accept(Record record)  {
        RelativeJsonPathScan scan = new RelativeJsonPathScan(record.getPath(), record.getValue(), configuration);
        this.walker.walk(scan, this.tree);
        return scan.exists();
    }
}
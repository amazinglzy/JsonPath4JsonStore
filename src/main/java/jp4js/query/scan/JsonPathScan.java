package jp4js.query.scan;

import jp4js.parser.JsonPathParser.JsonAbsolutePathExprContext;
import jp4js.utils.Configuration;
import jp4js.query.RecordSet;
import jp4js.query.RecordSet.Record;

import java.util.Iterator;


public class JsonPathScan extends AbstractJPScan {
    private Object json;

    public JsonPathScan(Object json, Configuration configuration) {
        super(configuration);
        this.json = json;
        this.generator = null;
    }
    
    public Iterator<Record> results() {
        return this.generator.data().iterator();
    }

    @Override
    public void enterJsonAbsolutePathExpr(JsonAbsolutePathExprContext ctx) {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append("$", json);
        this.generator = new RecordGenerator(recordSet, configuration);
    }
}
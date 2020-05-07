package jp4js.query.baseline;

import jp4js.parser.JsonPathParser.JsonAbsolutePathExprContext;
import jp4js.utils.Configuration;
import jp4js.query.RecordSet;
import jp4js.query.RecordSet.Record;
import jp4js.utils.iter.Iter;


public class NaiveAbsolute extends AbstractNaive {
    private Object json;

    public NaiveAbsolute(Object json, Configuration configuration) {
        super(configuration);
        this.json = json;
    }
    
    public Iter<Record> results() {
        return this.planOp.iterator();
    }

    @Override
    public Void visitJsonAbsolutePathExpr(JsonAbsolutePathExprContext ctx) {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append("$", json);
        this.planOp = recordSet;
        return visitChildren(ctx);
    }
}
package jp4js.query.baseline;

import jp4js.parser.JsonPathParser.*;
import jp4js.query.RecordSet;
import jp4js.utils.Configuration;

public class NaiveRelative extends AbstractNaive {
    private String path;
    private Object value;
    
    public NaiveRelative(String path, Object value, Configuration configuration) {
        super(configuration);
        this.path = path;
        this.value = value;
    }

    public NaiveRelative(Object value, Configuration configuration) {
        super(configuration);
        this.path = "@";
        this.value = value;
    }

    public boolean exists() {
        assert(this.planOp != null);
        return this.planOp.iterator().hasNext();
    }

    @Override
    public Void visitJsonRelativePathExpr(JsonRelativePathExprContext ctx) {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append(this.path, this.value);
        this.planOp = recordSet;
        return visitChildren(ctx);
    }
}
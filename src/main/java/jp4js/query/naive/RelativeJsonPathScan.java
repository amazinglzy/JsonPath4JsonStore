package jp4js.query.naive;

import jp4js.parser.JsonPathParser.*;
import jp4js.query.RecordSet;
import jp4js.utils.Configuration;

public class RelativeJsonPathScan extends AbstractJPScan {
    private String path;
    private Object value;
    
    public RelativeJsonPathScan(String path, Object value, Configuration configuration) {
        super(configuration);
        this.path = path;
        this.value = value;
    }

    public RelativeJsonPathScan(Object value, Configuration configuration) {
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
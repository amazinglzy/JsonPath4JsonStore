package jp4js.query.join;

import jp4js.index.IndexContext;
import jp4js.parser.JsonPathBaseListener;
import jp4js.parser.JsonPathParser;
import jp4js.query.ArraySelectionsVisitor;
import jp4js.query.IndexArrayScan;
import jp4js.query.IndexPropertyScan;
import jp4js.query.IndexTokenScan;
import jp4js.query.PlanOperator;

public class MergeJoin extends JsonPathBaseListener {
    private PlanOperator planOp;
    private IndexContext indexContext;
    public MergeJoin(IndexContext indexContext) {
        this.indexContext = indexContext;
        this.planOp = null;
    }

    public PlanOperator operator() {
        return this.planOp;
    }

    @Override
    public void enterJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        this.planOp = new IndexPropertyScan(indexContext, "$");
    }

    @Override
    public void enterJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.planOp = new PCJoin(
            this.planOp, 
            new IndexTokenScan(indexContext)
        );
    }

    @Override
    public void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.planOp = new ADJoin(
            this.planOp,
            new IndexPropertyScan(
                this.indexContext,
                ctx.jsonFieldName().getText()
            )
        );
    }

    @Override
    public void enterJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.planOp = new PCJoin(
            this.planOp, 
            new IndexTokenScan(indexContext)
        );
    }

	@Override
    public void enterJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        this.planOp = new PCJoin(
            this.planOp, 
            new IndexArrayScan(
                this.indexContext,
                visitor.visit(ctx)
            )
        );
    }
}
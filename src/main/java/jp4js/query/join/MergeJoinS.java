package jp4js.query.join;

import jp4js.index.IndexContext;
import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.index.node.LabelNode;
import jp4js.parser.JsonPathBaseListener;
import jp4js.parser.JsonPathParser;
import jp4js.query.ArraySelectionsVisitor;
import jp4js.query.IndexArrayScan;
import jp4js.query.IndexPropertyScan;
import jp4js.query.IndexTokenScan;
import jp4js.query.PlanOperator;

import java.util.List;
import java.util.LinkedList;

public class MergeJoinS extends JsonPathBaseListener {
    private PlanOperator<Item> planOp;
    private IndexContext indexContext;
    public MergeJoinS(IndexContext indexContext) {
        this.indexContext = indexContext;
        this.planOp = null;
    }

    public PlanOperator<LabelNode> operator() {
        return new Reorder(this.planOp);
    }

    @Override
    public void enterJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        this.planOp = new NormalWrapper(
            new IndexPropertyScan(indexContext, "$")
        );
    }

    @Override 
    public void enterJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        this.planOp = new PCJoin(
            planOp,
            new NormalWrapper(
                new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText())
            )
        );
    }

    @Override
    public void enterJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.planOp = new PCJoin(
            planOp, 
            new NormalWrapper(
                new IndexTokenScan(indexContext)
            )
        );
    }

    @Override
    public void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.planOp = new ADJoin(
            planOp,
            new NormalWrapper(
                new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText())
            )
        );
    }

    @Override
    public void enterJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.planOp = new PCJoin(
            planOp, 
            new NormalWrapper(
                new IndexTokenScan(indexContext)
            )
        );
    }

	@Override
    public void enterJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        ArraySelections selections = visitor.visit(ctx);
        List<ArraySelections> singleSelections = selections.asList();
        List<PlanOperator<Item>> planOps = new LinkedList<>(){{
            for (ArraySelections s: singleSelections) {
                add(new NormalWrapper(new IndexArrayScan(indexContext, s)));
            }
        }};
        this.planOp = new PCJoin(this.planOp, new Collector(planOps));
    }
}
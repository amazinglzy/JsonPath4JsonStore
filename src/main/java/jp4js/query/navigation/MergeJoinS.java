package jp4js.query.navigation;

import jp4js.index.IndexContext;
import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.index.node.LabelNode;
import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.utils.query.ArraySelectionsVisitor;
import jp4js.query.IndexArrayScan;
import jp4js.query.IndexPropertyScan;
import jp4js.query.IndexTokenScan;
import jp4js.query.PlanOperator;
import jp4js.query.baseline.FilterVisitor;
import jp4js.utils.filter.Filter;

import java.util.List;
import java.util.LinkedList;

public class MergeJoinS extends JsonPathBaseVisitor<Void> {
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
    public Void visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        this.planOp = new NormalWrapper(
            new IndexPropertyScan(indexContext, "$")
        );
        visitChildren(ctx);
        return null;
    }

    @Override 
    public Void visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        this.planOp = new PCJoin(
            planOp,
            new NormalWrapper(
                new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText())
            )
        );
        visitChildren(ctx);
        return null;
    }

    @Override
    public Void visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.planOp = new PCJoin(
            planOp, 
            new NormalWrapper(
                new IndexTokenScan(indexContext)
            )
        );
        visitChildren(ctx);
        return null;
    }

    @Override
    public Void visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.planOp = new ADJoin(
            planOp,
            new NormalWrapper(
                new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText())
            )
        );
        visitChildren(ctx);
        return null;
    }

    @Override
    public Void visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.planOp = new PCJoin(
            planOp, 
            new NormalWrapper(
                new IndexTokenScan(indexContext)
            )
        );
        visitChildren(ctx);
        return null;
    }

	@Override
    public Void visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        ArraySelections selections = visitor.visit(ctx);
        List<ArraySelections> singleSelections = selections.asList();
        List<PlanOperator<Item>> planOps = new LinkedList<>(){{
            for (int i = 0; i < singleSelections.size(); i++) {
                add(
                    new SelectionWrapper(
                        new IndexArrayScan(indexContext, singleSelections.get(i)),
                        i
                    )
                );
            }
        }};
        this.planOp = new PCJoin(this.planOp, new Collector(planOps));
        visitChildren(ctx);
        return null;
    }

    @Override 
    public Void visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) {
        FilterVisitor<Item> visitor = new FilterVisitor<Item>(this.indexContext.configuration());
        Filter<Item> filter = visitor.visit(ctx.jsonCond());
        this.planOp = new FilterPlanOperator(this.planOp, filter);
        return null;
    }
}
package jp4js.query.navigation;

import jp4js.index.IndexContext;
import jp4js.index.node.LabelArray.ArraySelections;
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

public class MergeJoinD extends JsonPathBaseVisitor<Void> {
    private List<PlanOperator<Item>> planOps;
    private IndexContext indexContext;
    public MergeJoinD(IndexContext indexContext) {
        this.planOps = null;
        this.indexContext = indexContext;
    }

    public List<PlanOperator<Item>> operators() {
        return this.planOps;
    }

    @Override
    public Void visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        this.planOps = new LinkedList<>(){{
            add(new NormalWrapper(new IndexPropertyScan(indexContext, "$")));
        }};
        visitChildren(ctx);
        return null;
    }

    @Override 
    public Void visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        this.planOps = new LinkedList<>() {{
            for (PlanOperator<Item> op: planOps) {
                add(new PCJoin(op, new NormalWrapper(new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText()))));
            }
        }};
        visitChildren(ctx);
        return null;
    }

    @Override
    public Void visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.planOps = new LinkedList<>(){{
            for (PlanOperator<Item> op: planOps) {
                add(new PCJoin(op, new NormalWrapper(new IndexTokenScan(indexContext))));
            }
        }};
        visitChildren(ctx);
        return null;
    }

    @Override
    public Void visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.planOps = new LinkedList<>(){{
            for (PlanOperator<Item> op: planOps) {
                add(new ADJoin(op, new NormalWrapper(new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText()))));
            }
        }};
        visitChildren(ctx);
        return null;
    }

    @Override
    public Void visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.planOps = new LinkedList<>() {{
            for (PlanOperator<Item> op: planOps) {
                add(new PCJoin(op, new NormalWrapper(new IndexTokenScan(indexContext))));
            }
        }};
        visitChildren(ctx);
        return null;
    }

	@Override
    public Void visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        ArraySelections selections = visitor.visit(ctx);
        List<ArraySelections> singleSelections = selections.asList();
        this.planOps = new LinkedList<>() {{
            for (PlanOperator<Item> op: planOps) {
                for (ArraySelections s: singleSelections) {
                    add(new PCJoin(op, new NormalWrapper(new IndexArrayScan(indexContext, s))));
                }
            }
        }};
        visitChildren(ctx);
        return null;
    }

    @Override 
    public Void visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) {
        FilterVisitor<Item> visitor = new FilterVisitor<Item>(this.indexContext.configuration());
        Filter<Item> filter = visitor.visit(ctx.jsonCond());
        this.planOps = new LinkedList<>() {{
            for (PlanOperator<Item> op: planOps) {
                add(new FilterPlanOperator(op, filter));
            }
        }};
        return null;
    }
}
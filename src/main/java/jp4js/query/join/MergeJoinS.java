package jp4js.query.join;

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
import jp4js.query.scan.FilterVisitor;
import jp4js.utils.Configuration;
import jp4js.utils.filter.Filter;

import java.util.List;
import java.util.LinkedList;

public class MergeJoinS extends JsonPathBaseVisitor<Void> {
    private PlanOperator<Item> planOp;
    private IndexContext indexContext;
    private Configuration configuration;
    public MergeJoinS(IndexContext indexContext, Configuration configuration) {
        this.indexContext = indexContext;
        this.planOp = null;
        this.configuration = configuration;
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
            for (ArraySelections s: singleSelections) {
                add(new NormalWrapper(new IndexArrayScan(indexContext, s)));
            }
        }};
        this.planOp = new PCJoin(this.planOp, new Collector(planOps));
        visitChildren(ctx);
        return null;
    }

    @Override 
    public Void visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) {
        FilterVisitor<Item> visitor = new FilterVisitor<Item>(configuration);
        Filter<Item> filter = visitor.visit(ctx.jsonCond());
        this.planOp = new FilterPlanOperator(this.planOp, filter);
        return null;
    }
}
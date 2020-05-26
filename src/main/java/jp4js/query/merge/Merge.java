package jp4js.query.merge;

import java.lang.module.Configuration;

import jp4js.index.IndexContext;
import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.query.IndexArrayScan;
import jp4js.query.IndexPropertyScan;
import jp4js.query.IndexTokenScan;
import jp4js.query.PlanOperator;
// import jp4js.query.baseline.FilterVisitor;
import jp4js.utils.query.ArraySelectionsVisitor;

public class Merge extends JsonPathBaseVisitor<PlanOperator<Item>> {
    private IndexContext indexContext;
    private Configuration configuration;
    private PlanOperator<Item> currentPlanOp;

    public Merge(IndexContext indexContext, Configuration configuration) {
        this.indexContext = indexContext;
        this.configuration = configuration;
    }

    public Merge(IndexContext indexContext, PlanOperator<Item> planOp, Configuration configuration) {
        this.indexContext = indexContext;
        this.currentPlanOp = planOp;
        this.configuration = configuration;
    }

    @Override 
    public PlanOperator<Item> visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        this.currentPlanOp = MergePlanOp.createLabelNode2Item(
            new IndexPropertyScan(indexContext, "$")
        );
        if (ctx.jsonSteps() != null) 
            return visit(ctx.jsonSteps());
        return this.currentPlanOp;
    }

    @Override 
    public PlanOperator<Item> visitJsonSteps(JsonPathParser.JsonStepsContext ctx) { 
        // visit all JsonObjectStep
        // creata Join for them
        return null;
    }

    @Override 
    public PlanOperator<Item> visitJsonObjectStep(JsonPathParser.JsonObjectStepContext ctx) { 
        // check if has filter
        // do filter
        return visitChildren(ctx);
    }

    @Override 
    public PlanOperator<Item> visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        PlanOperator<Item> newPlanOp = MergePlanOp.createLabelNode2Item(new IndexTokenScan(indexContext));
        PlanOperator<Item> ret = MergePlanOp.createPCJoin(this.currentPlanOp, newPlanOp);
        this.currentPlanOp = newPlanOp;
        return ret;
    }

    @Override 
    public PlanOperator<Item> visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) { 
        PlanOperator<Item> newPlanOp = MergePlanOp.createLabelNode2Item(
            new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText()));
        PlanOperator<Item> ret = MergePlanOp.createPCJoin(this.currentPlanOp, newPlanOp);
        this.currentPlanOp = newPlanOp;
        return ret;
    }

    @Override 
    public PlanOperator<Item> visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) { 
        PlanOperator<Item> newPlanOp = MergePlanOp.createLabelNode2Item(
            new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText())
        );
        PlanOperator<Item> ret = MergePlanOp.createADJoin(this.currentPlanOp, newPlanOp);
        this.currentPlanOp = newPlanOp;
        return ret;
    }

    @Override 
    public PlanOperator<Item> visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        PlanOperator<Item> newPlanOp = MergePlanOp.createLabelNode2Item(new IndexTokenScan(indexContext));
        PlanOperator<Item> ret = MergePlanOp.createPCJoin(this.currentPlanOp, newPlanOp);
        this.currentPlanOp = newPlanOp;
        return ret;
    }

    @Override 
    public PlanOperator<Item> visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) { 
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        ArraySelections selections = visitor.visit(ctx);
        PlanOperator<Item> newPlanOp = MergePlanOp.createLabelNode2Item(
            new IndexArrayScan(indexContext, selections)
        );
        PlanOperator<Item> ret = MergePlanOp.createPCJoin(this.currentPlanOp, newPlanOp);
        this.currentPlanOp = newPlanOp;
        return ret;
    }
}
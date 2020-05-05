package jp4js.query.naive;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.index.node.LabelArray.*;
import jp4js.utils.query.ArraySelectionsVisitor;
import jp4js.utils.Configuration;
import jp4js.utils.filter.Filter;
import jp4js.query.PlanOperator;
import jp4js.query.RecordSet.Record;
import jp4js.query.naive.RecordGenerator.*;
import java.util.LinkedList;

public abstract class AbstractJPScan extends JsonPathBaseVisitor<Void> {
    protected Configuration configuration;
    protected PlanOperator<Record> planOp;
    public AbstractJPScan(Configuration configuration) {
        this.planOp = null;
        this.configuration = configuration;
    }

    @Override
	public Void visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.planOp = new WildcardPlanOperator(this.planOp, configuration);
        return visitChildren(ctx);
    }

    @Override
	public Void visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) { 
        this.planOp = new PropertyPlanOperator(
            planOp, 
            new LinkedList<>(){{
                add(ctx.jsonFieldName().IDENTIFIER().getText());
            }}, 
            configuration
        );
        return visitChildren(ctx);
    }

    @Override 
    public Void visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) { 
        this.planOp = new ScanPlanOperator(
            this.planOp, 
            new LinkedList<>(){{
                add(ctx.jsonFieldName().IDENTIFIER().getText());
            }}, 
            configuration
        );
        return visitChildren(ctx);
    }

    @Override
    public Void visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) {
        this.planOp = new WildcardPlanOperator(
            planOp, 
            configuration
        );
        return visitChildren(ctx);
    }

    @Override
    public Void visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        ArraySelections selections = visitor.visit(ctx);
        this.planOp = new ArraySelectionsPlanOperator(
            this.planOp, 
            selections, 
            configuration);
        return visitChildren(ctx);
    }

    @Override 
    public Void visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) {
        FilterVisitor<Record> visitor = new FilterVisitor<Record>(configuration);
        Filter<Record> filter = visitor.visit(ctx.jsonCond());
        this.planOp = new FilterPlanOperator(
            planOp, 
            filter, 
            configuration);
        return null;
    }
}
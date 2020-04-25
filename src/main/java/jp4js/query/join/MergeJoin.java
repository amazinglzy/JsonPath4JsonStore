package jp4js.query.join;

import jp4js.index.IndexContext;
import jp4js.index.node.ArrayNode.ArraySelections;
import jp4js.parser.JsonPathBaseListener;
import jp4js.parser.JsonPathParser;
import jp4js.query.ArraySelectionsVisitor;
import jp4js.query.IndexArrayScan;
import jp4js.query.IndexPropertyScan;
import jp4js.query.IndexTokenScan;
import jp4js.query.PlanOperator;

import java.util.List;
import java.util.LinkedList;

public class MergeJoin extends JsonPathBaseListener {
    private List<PlanOperator> planOps;
    private IndexContext indexContext;
    public MergeJoin(IndexContext indexContext) {
        this.indexContext = indexContext;
        this.planOps = null;
    }

    public List<PlanOperator> operators() {
        return this.planOps;
    }

    @Override
    public void enterJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        this.planOps = new LinkedList<>(){{
            add(new IndexPropertyScan(indexContext, "$"));
        }};
    }

    @Override
    public void enterJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.planOps = new LinkedList<>(){{
            for (PlanOperator op: planOps) {
                add(new PCJoin(op, new IndexTokenScan(indexContext)));
            }
        }};
    }

    @Override
    public void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.planOps = new LinkedList<>(){{
            for (PlanOperator op: planOps) {
                add(new ADJoin(op, new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText())));
            }
        }};
    }

    @Override
    public void enterJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.planOps = new LinkedList<>() {{
            for (PlanOperator op: planOps) {
                add(new PCJoin(op, new IndexTokenScan(indexContext)));
            }
        }};
    }

	@Override
    public void enterJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        ArraySelections selections = visitor.visit(ctx);
        List<ArraySelections> singleSelections = selections.asList();
        this.planOps = new LinkedList<>() {{
            for (PlanOperator op: planOps) {
                for (ArraySelections s: singleSelections) {
                    add(new PCJoin(op, new IndexArrayScan(indexContext, s)));
                }
            }
        }};
    }
}
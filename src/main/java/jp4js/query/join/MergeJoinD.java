package jp4js.query.join;

import jp4js.index.IndexContext;
import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.parser.JsonPathBaseListener;
import jp4js.parser.JsonPathParser;
import jp4js.query.ArraySelectionsVisitor;
import jp4js.query.IndexArrayScan;
import jp4js.query.IndexPropertyScan;
import jp4js.query.IndexTokenScan;
import jp4js.query.PlanOperator;

import java.util.List;
import java.util.LinkedList;

public class MergeJoinD extends JsonPathBaseListener {
    private List<PlanOperator<Item>> planOps;
    private IndexContext indexContext;
    public MergeJoinD(IndexContext indexContext) {
        this.indexContext = indexContext;
        this.planOps = null;
    }

    public List<PlanOperator<Item>> operators() {
        return this.planOps;
    }

    @Override
    public void enterJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        this.planOps = new LinkedList<>(){{
            add(new NormalWrapper(new IndexPropertyScan(indexContext, "$")));
        }};
    }

    @Override 
    public void enterJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        this.planOps = new LinkedList<>() {{
            for (PlanOperator<Item> op: planOps) {
                add(new PCJoin(op, new NormalWrapper(new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText()))));
            }
        }};
    }

    @Override
    public void enterJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.planOps = new LinkedList<>(){{
            for (PlanOperator<Item> op: planOps) {
                add(new PCJoin(op, new NormalWrapper(new IndexTokenScan(indexContext))));
            }
        }};
    }

    @Override
    public void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.planOps = new LinkedList<>(){{
            for (PlanOperator<Item> op: planOps) {
                add(new ADJoin(op, new NormalWrapper(new IndexPropertyScan(indexContext, ctx.jsonFieldName().getText()))));
            }
        }};
    }

    @Override
    public void enterJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.planOps = new LinkedList<>() {{
            for (PlanOperator<Item> op: planOps) {
                add(new PCJoin(op, new NormalWrapper(new IndexTokenScan(indexContext))));
            }
        }};
    }

	@Override
    public void enterJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
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
    }
}
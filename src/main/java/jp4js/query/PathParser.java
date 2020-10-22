package jp4js.query;

import jp4js.query.parser.JsonPathBaseVisitor;
import jp4js.query.parser.JsonPathParser;
import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;


public class PathParser extends JsonPathBaseVisitor<StructureList> {
    private StructureList lst;
    private StructureSteps steps;
    public PathParser() {
        this.lst = null;
        this.steps = new StructureSteps();
    }

    @Override
    public StructureList visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        steps.reverse();
        StructureList ret = new StructureList();
        ret.put(steps, this.lst);
        return new StructureList(new StructureSteps(), ret);
    }

    @Override 
    public StructureList visitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        StructureList ret = new StructureList();
        ret.put(steps, this.lst);
        return new StructureList(new StructureSteps(), ret);
    }

    @Override 
    public StructureList visitJsonStep(JsonPathParser.JsonStepContext ctx) { 
        if (ctx.jsonFilterExpr() != null) {
            if (this.steps.size() != 0) {
                this.steps.reverse();
                StructureList lst = new StructureList();
                lst.put(this.steps, this.lst);
                this.lst = lst;
                this.steps = new StructureSteps();
            }

            StructureList brothers = new FilterPathParser().visit(ctx.jsonFilterExpr());
            if (this.lst != null) {
                this.lst.mergeIn(brothers);
            } else {
                this.lst = brothers;
            }
            this.lst = new StructureList(this.lst);
        }
        this.visit(ctx.getChild(0) );
        return null;
    }

    @Override 
    public StructureList visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        this.steps.addStep(StructureRelation.PC, ctx.jsonFieldName().getText());
        return null;
    }

    @Override
    public StructureList visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.steps.addStep(StructureRelation.PC, "*");
        return null;
    }

    @Override
    public StructureList visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.steps.addStep(StructureRelation.AD, ctx.jsonFieldName().getText());
        return null;
    }

    @Override 
    public StructureList visitJsonArrayStep(JsonPathParser.JsonArrayStepContext ctx) { 
        if (ctx.jsonArrayWildcardStep() != null) 
            return this.visit(ctx.jsonArrayWildcardStep());
        else
            return this.visit(ctx.jsonArraySelectionsStep());
    }

    @Override
    public StructureList visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.steps.addStep(StructureRelation.PC, "*");
        return null;
    }

	@Override
    public StructureList visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        int from = Integer.parseInt(ctx.jsonArraySlice().NATRUAL_INTEGER(0).getText());
        int to = Integer.parseInt(ctx.jsonArraySlice().NATRUAL_INTEGER(1).getText());
        StructureSteps.IndexStep istep = new StructureSteps.IndexStep(from, to);
        this.steps.addStep(istep);
        return null;
    }
}
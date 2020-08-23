package jp4js.query;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.nf2.op.structure.RepeatableSL;
import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.op.structure.StructureRelation;
import jp4js.nf2.op.structure.StructureSteps;


public class PathParser extends JsonPathBaseVisitor<StructureList> {
    private RepeatableSL lst;
    private StructureSteps steps;
    public PathParser() {
        this.lst = new RepeatableSL();
        this.steps = null;
    }

    @Override
    public StructureList visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        if (steps != null) {
            steps.reverse();
            RepeatableSL ret = new RepeatableSL();
            ret.put(steps, this.lst);
            return ret;
        } else {
            return new RepeatableSL(this.lst);
        }
    }

    @Override 
    public StructureList visitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        if (steps != null) {
            steps.reverse();
            RepeatableSL ret = new RepeatableSL();
            ret.put(steps, this.lst);
            return ret;
        } else {
            return this.lst;
        }
    }

    @Override 
    public StructureList visitJsonStep(JsonPathParser.JsonStepContext ctx) { 
        if (ctx.jsonFilterExpr() != null) {
            if (this.steps != null) {
                RepeatableSL nlst = new RepeatableSL();
                this.steps.reverse();
                nlst.put(steps, this.lst);
                this.lst = nlst;
                this.steps = null;
            }

            StructureList brothers = new FilterPathParser().visit(ctx.jsonFilterExpr());
            this.lst.mergeIn(brothers);
        }
        this.visit(ctx.getChild(0) );
        return null;
    }

    @Override 
    public StructureList visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        if (this.steps == null) {
            this.steps = new StructureSteps();
        }
        this.steps.addStep(StructureRelation.PC, ctx.jsonFieldName().getText());
        return null;
    }

    @Override
    public StructureList visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        if (this.steps == null) {
            this.steps = new StructureSteps();
        }
        this.steps.addStep(StructureRelation.PC, "*");
        return null;
    }

    @Override
    public StructureList visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        if (this.steps == null) {
            this.steps = new StructureSteps();
        }
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
        if (this.steps == null) {
            this.steps = new StructureSteps();
        }
        this.steps.addStep(StructureRelation.PC, "*");
        return null;
    }

	@Override
    public StructureList visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        if (this.steps == null) {
            this.steps = new StructureSteps();
        }
        int from = Integer.parseInt(ctx.jsonArraySlice().NATRUAL_INTEGER(0).getText());
        int to = Integer.parseInt(ctx.jsonArraySlice().NATRUAL_INTEGER(1).getText());
        StructureSteps.IndexStep istep = new StructureSteps.IndexStep(from, to);
        this.steps.addStep(istep);
        return null;
    }
}
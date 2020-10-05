package jp4js.query;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.algebra.op.structure.RepeatableSL;
import jp4js.algebra.op.structure.SingularSL;
import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;


public class PathParser extends JsonPathBaseVisitor<RepeatableSL> {
    private StructureList lst;
    private StructureSteps steps;
    public PathParser() {
        this.lst = null;
        this.steps = new StructureSteps();
    }

    @Override
    public RepeatableSL visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        steps.reverse();
        SingularSL ret = new SingularSL();
        ret.put(steps, this.lst);
        return new RepeatableSL(new StructureSteps(), ret);
    }

    @Override 
    public RepeatableSL visitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        SingularSL ret = new SingularSL();
        ret.put(steps, this.lst);
        return new RepeatableSL(new StructureSteps(), ret);
    }

    @Override 
    public RepeatableSL visitJsonStep(JsonPathParser.JsonStepContext ctx) { 
        if (ctx.jsonFilterExpr() != null) {
            if (this.steps.size() != 0) {
                this.steps.reverse();
                SingularSL lst = new SingularSL();
                lst.put(this.steps, this.lst);
                this.lst = lst;
                this.steps = new StructureSteps();
            }

            RepeatableSL brothers = new FilterPathParser().visit(ctx.jsonFilterExpr());
            if (this.lst != null) {
                brothers.mergeTo(this.lst);
            } else {
                this.lst = brothers.elemType();
            }
        }
        this.visit(ctx.getChild(0) );
        return null;
    }

    @Override 
    public RepeatableSL visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        this.steps.addStep(StructureRelation.PC, ctx.jsonFieldName().getText());
        return null;
    }

    @Override
    public RepeatableSL visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.steps.addStep(StructureRelation.PC, "*");
        return null;
    }

    @Override
    public RepeatableSL visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        this.steps.addStep(StructureRelation.AD, ctx.jsonFieldName().getText());
        return null;
    }

    @Override 
    public RepeatableSL visitJsonArrayStep(JsonPathParser.JsonArrayStepContext ctx) { 
        if (ctx.jsonArrayWildcardStep() != null) 
            return this.visit(ctx.jsonArrayWildcardStep());
        else
            return this.visit(ctx.jsonArraySelectionsStep());
    }

    @Override
    public RepeatableSL visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        this.steps.addStep(StructureRelation.PC, "*");
        return null;
    }

	@Override
    public RepeatableSL visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        int from = Integer.parseInt(ctx.jsonArraySlice().NATRUAL_INTEGER(0).getText());
        int to = Integer.parseInt(ctx.jsonArraySlice().NATRUAL_INTEGER(1).getText());
        StructureSteps.IndexStep istep = new StructureSteps.IndexStep(from, to);
        this.steps.addStep(istep);
        return null;
    }
}
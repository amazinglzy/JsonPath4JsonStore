package jp4js.query;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.nf2.op.structure.RepeatableSL;
import jp4js.nf2.op.structure.SingularSL;
// import jp4js.utils.query.ArraySelectionsVisitor;
// import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.op.structure.StructureRelation;


public class PathParser extends JsonPathBaseVisitor<StructureList> {
    private StructureList lst;
    public PathParser() {
        this.lst = null;
    }

    @Override
    public StructureList visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        if (lst != null) return lst;
        return new SingularSL();
    }

    @Override 
    public StructureList visitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx) { 
        for (int i = ctx.jsonSteps().jsonStep().size() - 1; i >= 0; i--) {
            this.visit(ctx.jsonSteps().jsonStep(i));
        }
        if (lst != null) return lst;
        return new SingularSL();
    }

    @Override 
    public StructureList visitJsonStep(JsonPathParser.JsonStepContext ctx) { 
        this.visit(ctx.getChild(0) );
        if (ctx.jsonFilterExpr() != null) {
            StructureList children = new FilterPathParser().visit(ctx.jsonFilterExpr());
            this.lst.mergeIn(children);
        }
        return this.lst;
    }

    @Override 
    public StructureList visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        StructureList nLst = new SingularSL();
        nLst.put(ctx.jsonFieldName().getText(), this.lst, StructureRelation.PC);
        this.lst = nLst;
        return this.lst;
    }

    @Override
    public StructureList visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        StructureList nLst = new SingularSL();
        nLst.put("*", this.lst, StructureRelation.PC);
        this.lst = nLst;
        return this.lst;
    }

    @Override
    public StructureList visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        StructureList nLst = new SingularSL();
        nLst.put(ctx.jsonFieldName().getText(), this.lst, StructureRelation.AD);
        this.lst = nLst;
        return this.lst;
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
        StructureList nLst;
        if (this.lst == null) 
            nLst = new RepeatableSL();
        else if (this.lst instanceof RepeatableSL) 
            nLst = new RepeatableSL((RepeatableSL)this.lst);
        else
            nLst = new RepeatableSL((SingularSL)this.lst);
        this.lst = nLst;
        return this.lst;
    }

	@Override
    public StructureList visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        // ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        // ArraySelections selections = visitor.visit(ctx);
        StructureList nLst;
        if (this.lst == null) 
            nLst = new RepeatableSL();
        else if (this.lst instanceof RepeatableSL) 
            nLst = new RepeatableSL((RepeatableSL)this.lst);
        else
            nLst = new RepeatableSL((SingularSL)this.lst);
        this.lst = nLst;
        return this.lst;
    }
}
package jp4js.query;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
// import jp4js.utils.query.ArraySelectionsVisitor;
// import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.op.structure.StructureRelation;
import jp4js.nf2.op.structure.StructureType;

import java.util.ArrayDeque;
import java.util.Deque;


public class PathParser extends JsonPathBaseVisitor<StructureList> {
    private Deque<StructureList> structurePath;
    private Deque<String> fieldPath;
    public PathParser() {
        this.structurePath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
    }

    @Override
    public StructureList visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        for (JsonPathParser.JsonStepContext jsonStep: ctx.jsonSteps().jsonStep()) {
            this.visit(jsonStep);
        }
        return this.structurePath.pop();
    }

    @Override 
    public StructureList visitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx) { 
        for (JsonPathParser.JsonStepContext jsonStep: ctx.jsonSteps().jsonStep()) {
            this.visit(jsonStep);
        }
        return this.structurePath.pop();
    }

    @Override 
    public StructureList visitJsonStep(JsonPathParser.JsonStepContext ctx) { 
        this.visit(ctx.getChild(0) );
        if (ctx.jsonFilterExpr() != null) {
            StructureList children = new FilterPathParser().visit(ctx.jsonFilterExpr());
            this.structurePath.peek().mergeIn(children);
        }
        return this.structurePath.peek();
    }

    @Override 
    public StructureList visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        StructureList lst = new StructureList(StructureType.SINGULAR);
        if (this.structurePath.size() > 0) {
            this.structurePath.peek().put(
                this.fieldPath.peek(), 
                lst, 
                StructureRelation.PC
            );
        }
        this.structurePath.push(lst);
        this.fieldPath.push(ctx.jsonFieldName().getText());
        return null;
    }

    @Override
    public StructureList visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        StructureList lst = new StructureList(StructureType.SINGULAR);
        if (this.structurePath.size() > 0) {
            this.structurePath.peek().put(
                this.fieldPath.peek(), 
                lst, 
                StructureRelation.PC
            );
        }
        this.structurePath.push(lst);
        this.fieldPath.push("*");
        return null;
    }

    @Override
    public StructureList visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        StructureList lst = new StructureList(StructureType.SINGULAR);
        if (this.structurePath.size() > 0) {
            this.structurePath.peek().put(
                this.fieldPath.peek(), 
                lst, 
                StructureRelation.AD
            );
        }
        this.structurePath.push(lst);
        this.fieldPath.push(ctx.jsonFieldName().getText());
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
        StructureList lst = new StructureList(StructureType.REPEATABLE);
        if (this.structurePath.size() > 0) {
            this.structurePath.peek().put(
                this.fieldPath.peek(), 
                lst, 
                StructureRelation.AD
            );
        }
        this.structurePath.push(lst);
        return null;
    }

	@Override
    public StructureList visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        // ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        // ArraySelections selections = visitor.visit(ctx);
        return null;
    }
}
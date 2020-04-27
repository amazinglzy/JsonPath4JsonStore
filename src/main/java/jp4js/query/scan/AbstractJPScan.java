package jp4js.query.scan;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.query.ArraySelectionsVisitor;
import jp4js.utils.Configuration;
import jp4js.utils.filter.Filter;
import jp4js.query.RecordSet.Record;
import java.util.LinkedList;

public abstract class AbstractJPScan extends JsonPathBaseVisitor<Void> {
    protected RecordGenerator generator;
    protected Configuration configuration;
    public AbstractJPScan(Configuration configuration) {
        this.generator = null;
        this.configuration = configuration;
    }

    @Override
	public Void visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.generator.stepWildcard();
        return visitChildren(ctx);
    }

    @Override
	public Void visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) { 
        this.generator.step(new LinkedList<>(){{
            add(ctx.jsonFieldName().IDENTIFIER().getText());
        }});
        return visitChildren(ctx);
    }

    @Override 
    public Void visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) { 
        this.generator.stepScan(new LinkedList<>(){{
            add(ctx.jsonFieldName().IDENTIFIER().getText());
        }});
        return visitChildren(ctx);
    }

    @Override
    public Void visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) {
        this.generator.stepWildcard();
        return visitChildren(ctx);
    }

    @Override
    public Void visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        this.generator.step(visitor.visit(ctx));
        return visitChildren(ctx);
    }

    @Override 
    public Void visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) {
        FilterVisitor visitor = new FilterVisitor(configuration);
        Filter<Record> filter = visitor.visit(ctx.jsonCond());
        this.generator.filter(filter);
        return null;
    }
}
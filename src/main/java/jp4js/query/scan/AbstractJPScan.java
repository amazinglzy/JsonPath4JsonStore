package jp4js.query.scan;

import jp4js.parser.JsonPathBaseListener;
import jp4js.parser.JsonPathParser;
import jp4js.query.ArraySelectionsVisitor;
import jp4js.utils.Configuration;
import jp4js.utils.filter.Filter;
import jp4js.query.RecordSet.Record;
import java.util.LinkedList;

public abstract class AbstractJPScan extends JsonPathBaseListener {
    protected RecordGenerator generator;
    protected Configuration configuration;
    public AbstractJPScan(Configuration configuration) {
        this.generator = null;
        this.configuration = configuration;
    }

    @Override
	public void enterJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.generator.stepWildcard();
    }

    @Override
	public void enterJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) { 
        this.generator.step(new LinkedList<>(){{
            add(ctx.jsonFieldName().IDENTIFIER().getText());
        }});
    }

    @Override 
    public void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) { 
        this.generator.stepScan(new LinkedList<>(){{
            add(ctx.jsonFieldName().IDENTIFIER().getText());
        }});
    }

    @Override
    public void enterJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) {
        this.generator.stepWildcard();
    }

    @Override
    public void enterJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        this.generator.step(visitor.visit(ctx));
    }

    @Override 
    public void enterJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) {
        FilterVisitor visitor = new FilterVisitor(configuration);
        Filter<Record> filter = visitor.visit(ctx);
        this.generator.filter(filter);
    }
}
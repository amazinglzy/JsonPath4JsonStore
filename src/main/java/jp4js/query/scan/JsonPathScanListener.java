package jp4js.query.scan;

import jp4js.parser.JsonPathBaseListener;
import jp4js.parser.JsonPathParser;
import jp4js.utils.Configuration;
import jp4js.index.node.ArrayNode.*;
import jp4js.query.ArraySelectionsVisitor;
import jp4js.query.RecordSet;
import jp4js.query.RecordSet.Record;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;


public class JsonPathScanListener extends JsonPathBaseListener {
    private Configuration configuration;
    private RecordGenerator generator;
    private Object json;

    public JsonPathScanListener(Object json, Configuration configuration) {
        this.configuration = configuration;
        this.json = json;
        this.generator = null;
    }
    
    public Iterator<Record> results() {
        return this.generator.data().iterator();
    }

	public void enterJsonBasicPathExpr(JsonPathParser.JsonBasicPathExprContext ctx) {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append("$", json);
        this.generator = new RecordGenerator(recordSet, configuration);
    }

	public void enterJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        this.generator.stepWildcard();
    }

	public void enterJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) { 
        this.generator.step(new LinkedList<>(){{
            add(ctx.jsonFieldName().IDENTIFIER().getText());
        }});
    }


	@Override public void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) { 
        this.generator.stepScan(new LinkedList<>(){{
            add(ctx.jsonFieldName().IDENTIFIER().getText());
        }});
    }

	@Override public void enterJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) {
        this.generator.stepWildcard();
    }

    @Override public void enterJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        this.generator.step(visitor.visit(ctx));
    }
}
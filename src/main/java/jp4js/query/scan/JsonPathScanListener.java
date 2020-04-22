package jp4js.query.scan;

import jp4js.parser.JsonPathBaseListener;
import jp4js.parser.JsonPathParser;
import jp4js.utils.Configuration;
import jp4js.index.node.ArrayNode.*;
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
        recordSet.append(new Record("$", json, configuration));
        this.generator = new RecordGenerator(recordSet, configuration);
    }

	public void enterJsonObjectStep(JsonPathParser.JsonObjectStepContext ctx) {
        if (ctx.WILDCARD() != null) {
            this.generator.stepWildcard();
        } else if (ctx.jsonFieldName() != null) {
            this.generator.step(new LinkedList<>(){{
                add(ctx.jsonFieldName().IDENTIFIER().getText());
            }});
        } else {
            assert(false);
        }
    }


	@Override public void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) { 
        this.generator.stepScan(new LinkedList<>(){{
            add(ctx.jsonFieldName().IDENTIFIER().getText());
        }});
    }

	@Override public void enterJsonArrayStep(JsonPathParser.JsonArrayStepContext ctx) { 
        if (ctx.WILDCARD() != null) {
            this.generator.stepWildcard();
        } else {
            List<ArraySelections> selections = new LinkedList<>();
            for (JsonPathParser.JsonArraySelectionContext sCtx: ctx.jsonArraySelection()) {
                if (sCtx.jsonArrayIndex() != null) {
                    selections.add(new ArrayIndex(Integer.valueOf(sCtx.getText())));
                } else if (sCtx.jsonArraySlice() != null) {
                    selections.add(
                        new ArraySlice(
                            Integer.valueOf(sCtx.jsonArraySlice().NATRUAL_INTEGER(0).getText()),
                            Integer.valueOf(sCtx.jsonArraySlice().NATRUAL_INTEGER(1).getText())
                        )
                    );
                }
            }
            this.generator.step(new ArrayOperation(selections));
        }   
    }
}
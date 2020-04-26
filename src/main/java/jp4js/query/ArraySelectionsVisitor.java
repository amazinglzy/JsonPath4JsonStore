package jp4js.query;

import java.util.LinkedList;

import jp4js.index.node.LabelArray.*;
import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;


public class ArraySelectionsVisitor extends JsonPathBaseVisitor<ArraySelections> {
    @Override public ArraySelections visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) { 
        LinkedList<ArraySelections> selections = new LinkedList<>();
        for (JsonPathParser.JsonArraySelectionContext sCtx: ctx.jsonArraySelection()) {
            selections.add( visit(sCtx) );
        }
        return new ArrayOperation(selections);
    }

    @Override public ArraySelections visitJsonArrayIndex(JsonPathParser.JsonArrayIndexContext ctx) { 
        return new ArrayIndex(Integer.valueOf(ctx.NATRUAL_INTEGER().getText()));
    }

	@Override public ArraySelections visitJsonArraySlice(JsonPathParser.JsonArraySliceContext ctx) { 
        return new ArraySlice(
            Integer.valueOf(ctx.NATRUAL_INTEGER(0).getText()),
            Integer.valueOf(ctx.NATRUAL_INTEGER(1).getText())
        );
    }
}

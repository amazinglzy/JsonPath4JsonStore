package jp4js.query;

import jp4js.nf2.op.structure.RepeatableSL;
import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;


public class FilterPathParser extends JsonPathBaseVisitor<RepeatableSL> {
    @Override 
    public RepeatableSL visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) { 
        return this.visit(ctx.jsonCond());
    }
    
    @Override
    public RepeatableSL visitJsonCondExists(JsonPathParser.JsonCondExistsContext ctx) { 
        return new PathParser().visit(ctx.jsonRelativePathExpr());
    }

    @Override 
    public RepeatableSL visitJsonCondAnd(JsonPathParser.JsonCondAndContext ctx) { 
        RepeatableSL ret = this.visit(ctx.jsonCond(0));
        ret.mergeIn(this.visit(ctx.jsonCond(1)));
        return ret;
    }
}
package jp4js.query;

import jp4js.nf2.op.structure.StructureList;
import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;


public class FilterPathParser extends JsonPathBaseVisitor<StructureList> {
    @Override 
    public StructureList visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) { 
        return this.visit(ctx.jsonCond());
    }
    
    @Override
    public StructureList visitJsonCondExists(JsonPathParser.JsonCondExistsContext ctx) { 
        return new PathParser().visit(ctx.jsonRelativePathExpr());
    }

    @Override 
    public StructureList visitJsonCondAnd(JsonPathParser.JsonCondAndContext ctx) { 
        StructureList ret = this.visit(ctx.jsonCond(0));
        ret.mergeIn(this.visit(ctx.jsonCond(1)));
        return ret;
    }
}
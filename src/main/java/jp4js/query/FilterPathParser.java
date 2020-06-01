package jp4js.query;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.query.path.PathNode;

import java.util.LinkedList;
import java.util.List;

public class FilterPathParser extends JsonPathBaseVisitor<List<PathNode>> {
    @Override 
    public List<PathNode> visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx) { 
        return this.visit(ctx.jsonCond());
    }
    
    @Override
    public List<PathNode> visitJsonCondExists(JsonPathParser.JsonCondExistsContext ctx) { 
        return new LinkedList<>() {{
            add(new PathParser().visit(ctx.jsonRelativePathExpr()));
        }};
    }

    @Override 
    public List<PathNode> visitJsonCondAnd(JsonPathParser.JsonCondAndContext ctx) { 
        return new LinkedList<>() {{
            addAll(FilterPathParser.this.visit(ctx.jsonCond(0)));
            addAll(FilterPathParser.this.visit(ctx.jsonCond(1)));
        }};
    }
}
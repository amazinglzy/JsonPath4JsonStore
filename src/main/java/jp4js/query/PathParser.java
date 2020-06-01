package jp4js.query;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.utils.query.ArraySelectionsVisitor;
import jp4js.index.node.LabelArray.ArraySelections;
import jp4js.query.path.ArrayNode;
import jp4js.query.path.DecendentNode;
import jp4js.query.path.PathNode;
import jp4js.query.path.PropertyNode;
import jp4js.query.path.RootNode;
import jp4js.query.path.WildcardNode;

import java.util.LinkedList;
import java.util.List;


public class PathParser extends JsonPathBaseVisitor<PathNode> {
    
    @Override
    public PathNode visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx) { 
        RootNode ret = new RootNode();
        PathNode previous = ret;
        for (JsonPathParser.JsonStepContext jsonStep: ctx.jsonSteps().jsonStep()) {
            PathNode node = this.visit(jsonStep);
            previous.add(node);
            previous = node;
        }
        return ret;
    }

    @Override 
    public PathNode visitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx) { 
        PathNode ret = null;
        PathNode previous = null;
        for (JsonPathParser.JsonStepContext jsonStep: ctx.jsonSteps().jsonStep()) {
            PathNode node = this.visit(jsonStep);
            if (previous == null) {
                previous = node;
                ret = node;
            } else {
                previous.add(node);
                previous = node;
            }
        }
        return ret;
    }

    @Override 
    public PathNode visitJsonStep(JsonPathParser.JsonStepContext ctx) { 
        PathNode ret = this.visit(ctx.getChild(0) );
        if (ctx.jsonFilterExpr() != null) {
            List<PathNode> children = new FilterPathParser().visit(ctx.jsonFilterExpr());
            for (PathNode child: children) {
                ret.add(child);
            }
        }
        return ret;
    }

    @Override 
    public PathNode visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx) {
        return new PropertyNode(new LinkedList<>(){{
            add(ctx.jsonFieldName().getText());
        }});
    }

    @Override
    public PathNode visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx) { 
        return new WildcardNode();
    }

    @Override
    public PathNode visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx) {
        return new DecendentNode(new LinkedList<>(){{
            add(ctx.jsonFieldName().getText());
        }});
    }

    @Override 
    public PathNode visitJsonArrayStep(JsonPathParser.JsonArrayStepContext ctx) { 
        if (ctx.jsonArrayWildcardStep() != null) 
            return this.visit(ctx.jsonArrayWildcardStep());
        else
            return this.visit(ctx.jsonArraySelectionsStep());
    }

    @Override
    public PathNode visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx) { 
        return new WildcardNode();
    }

	@Override
    public PathNode visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx) {
        ArraySelectionsVisitor visitor = new ArraySelectionsVisitor();
        ArraySelections selections = visitor.visit(ctx);
        return new ArrayNode(selections);
    }
}
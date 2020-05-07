package jp4js.query.baseline;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.utils.filter.Filter;
import jp4js.utils.filter.AndFilter;
import jp4js.utils.Configuration;
import jp4js.utils.Value;

import org.antlr.v4.runtime.tree.*;

public class FilterVisitor<E extends Value> extends JsonPathBaseVisitor<Filter<E>> {

    private Configuration configuration;
    public FilterVisitor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Filter<E> visitJsonCondExists(JsonPathParser.JsonCondExistsContext ctx) { 
        return new JsonPathExistsFilter<E>(ctx, configuration);
    }

    @Override 
    public Filter<E> visitJsonCondAnd(JsonPathParser.JsonCondAndContext ctx) { 
        return new AndFilter<E>(visit(ctx.jsonCond(0)), visit(ctx.jsonCond(1)));
    }

    public static class JsonPathExistsFilter<E extends Value> implements Filter<E> {
        private ParseTree tree;
        private Configuration configuration;

        public JsonPathExistsFilter(ParseTree tree, Configuration configuration) {
            this.tree = tree;
            this.configuration = configuration;
        }

        @Override
        public boolean accept(E record)  {
            NaiveRelative scan = new NaiveRelative(record.getValue(), configuration);
            scan.visit(this.tree);
            return scan.exists();
        }
    }
}
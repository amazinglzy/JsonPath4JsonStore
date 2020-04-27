package jp4js.query.scan;

import jp4js.parser.JsonPathBaseVisitor;
import jp4js.parser.JsonPathParser;
import jp4js.query.RecordSet.Record;
import jp4js.utils.filter.Filter;
import jp4js.utils.filter.AndFilter;
import jp4js.utils.Configuration;

public class FilterVisitor extends JsonPathBaseVisitor<Filter<Record>> {

    private Configuration configuration;
    public FilterVisitor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Filter<Record> visitJsonCondExists(JsonPathParser.JsonCondExistsContext ctx) { 
        return new JsonPathExistsFilter(ctx, configuration);
    }

    @Override 
    public Filter<Record> visitJsonCondAnd(JsonPathParser.JsonCondAndContext ctx) { 
        return new AndFilter<Record>(visit(ctx.jsonCond(0)), visit(ctx.jsonCond(1)));
    }
}
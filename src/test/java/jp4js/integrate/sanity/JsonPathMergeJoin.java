package jp4js.integrate.sanity;

import java.util.Iterator;
import jp4js.query.JsonPath;
import jp4js.query.RecordSet.Record;
import jp4js.utils.Configuration;

public class JsonPathMergeJoin implements JsonPathEval {

    @Override
    public Iterator<Record> eval(String queryPath, Object json, Configuration configuration) {
        return JsonPath.evaluateByMergeJoin(queryPath, json, configuration);
    }
}
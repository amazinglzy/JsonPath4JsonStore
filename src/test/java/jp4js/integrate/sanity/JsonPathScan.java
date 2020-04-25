package jp4js.integrate.sanity;

import jp4js.query.JsonPath;
import jp4js.utils.Configuration;
import jp4js.query.RecordSet.Record;
import java.util.Iterator;

public class JsonPathScan implements JsonPathEval {
    @Override
    public Iterator<Record> eval(String queryPath, Object json, Configuration configuration) {
        return JsonPath.evaluateByScan(queryPath, json, configuration);
    }
}
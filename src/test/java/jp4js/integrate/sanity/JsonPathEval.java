package jp4js.integrate.sanity;

import jp4js.query.RecordSet.Record;
import jp4js.utils.Configuration;

import java.util.Iterator;

public interface JsonPathEval {
    Iterator<Record> eval(String queryPath, Object json, Configuration configuration);
}
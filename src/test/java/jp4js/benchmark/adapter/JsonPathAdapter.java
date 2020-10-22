package jp4js.benchmark.adapter;

import com.jayway.jsonpath.Configuration;

public interface JsonPathAdapter {
    void index(Object json, Configuration configuration);
    int query(String query);
}

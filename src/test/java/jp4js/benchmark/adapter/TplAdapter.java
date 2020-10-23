package jp4js.benchmark.adapter;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.op.structure.StructureList;

public interface TplAdapter {
    void index(Object json, Configuration configuration);
    int query(StructureList query);
}

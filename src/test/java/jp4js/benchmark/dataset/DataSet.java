package jp4js.benchmark.dataset;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.op.structure.StructureList;

public interface DataSet {
    Object data();
    Configuration configuration();

    int querySize();
    String jsonPathQuery(int i);
    StructureList tplQuery(int i);
}

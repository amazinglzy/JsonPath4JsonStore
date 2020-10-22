package jp4js.benchmark.adapter;

import jp4js.algebra.op.structure.StructureList;

public interface TplAdapter {
    void index(String json);
    int query(StructureList query);
}

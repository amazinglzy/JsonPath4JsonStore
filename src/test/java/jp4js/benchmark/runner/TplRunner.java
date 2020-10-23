package jp4js.benchmark.runner;

import jp4js.algebra.op.structure.StructureList;
import jp4js.benchmark.adapter.TplAdapter;

public class TplRunner extends Runner {
    private TplAdapter adapter;
    private StructureList query;

    public TplRunner(TplAdapter adapter, StructureList query) {
        this.adapter = adapter;
        this.query = query;
    }

    @Override
    public void doTest() {
        this.adapter.query(query);
    }
}

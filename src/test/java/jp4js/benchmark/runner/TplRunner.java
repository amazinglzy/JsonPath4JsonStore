package jp4js.benchmark.runner;

import java.util.LinkedList;
import java.util.List;

import jp4js.algebra.operator.structure.StructureList;
import jp4js.benchmark.adapter.TplAdapter;

public class TplRunner extends Runner {
    private TplAdapter adapter;
    private StructureList query;
    private List<Integer> results;

    public TplRunner(TplAdapter adapter, StructureList query) {
        this.adapter = adapter;
        this.query = query;
        this.results = new LinkedList<>();
    }

    @Override
    public void doTest() {
        int r = this.adapter.query(query);
        this.results.add(r);
    }

    public List<Integer> results() {
        return this.results;
    }
}

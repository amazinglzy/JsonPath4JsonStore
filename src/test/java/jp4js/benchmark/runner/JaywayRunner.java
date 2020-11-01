package jp4js.benchmark.runner;

import java.util.LinkedList;
import java.util.List;

import jp4js.benchmark.adapter.JsonPathAdapter;

public class JaywayRunner extends Runner {
    private JsonPathAdapter adapter;
    private String query;
    private List<Integer> results;

    public JaywayRunner(JsonPathAdapter adapter, String query) {
        this.adapter = adapter;
        this.query = query;
        this.results = new LinkedList<>();
    }

    @Override
    public void doTest() {
        int r = this.adapter.query(this.query);
        results.add(r);
    }

    @Override
    public List<Integer> results() {
        return this.results;
    }
}

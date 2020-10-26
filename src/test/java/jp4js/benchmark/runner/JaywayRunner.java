package jp4js.benchmark.runner;

import jp4js.benchmark.adapter.JsonPathAdapter;

public class JaywayRunner extends Runner {
    private JsonPathAdapter adapter;
    private String query;

    public JaywayRunner(JsonPathAdapter adapter, String query) {
        this.adapter = adapter;
        this.query = query;
    }

    @Override
    public void doTest() {
        this.adapter.query(this.query);
    }
}

package jp4js.benchmark.runner;

import jp4js.benchmark.adapter.JaywayAdapter;

public class JaywayRunner extends Runner {
    private JaywayAdapter adapter;
    private String query;

    public JaywayRunner(JaywayAdapter adapter, String query) {
        this.adapter = adapter;
        this.query = query;
    }

    @Override
    public void doTest() {
        this.adapter.query(this.query);
    }
}

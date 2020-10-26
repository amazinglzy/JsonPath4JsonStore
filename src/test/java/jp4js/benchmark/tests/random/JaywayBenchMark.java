package jp4js.benchmark.tests.random;

import java.io.IOException;

import jp4js.benchmark.adapter.JaywayAdapter;
import jp4js.benchmark.adapter.JsonPathAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.Random;
import jp4js.benchmark.tests.JsonPathBenchMark;

public class JaywayBenchMark extends JsonPathBenchMark {
    @Override
    protected JsonPathAdapter adapter() {
        return new JaywayAdapter();
    }

    @Override
    protected DataSet dataSet() {
        Random random = new Random();
        try {
            random.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return random;
    }

    @Override
    protected String filename() {
        return "random/jayway.txt";
    }

    public static void main(String[] args) {
        JaywayBenchMark benchMark = new JaywayBenchMark();
        benchMark.run();
    }
}
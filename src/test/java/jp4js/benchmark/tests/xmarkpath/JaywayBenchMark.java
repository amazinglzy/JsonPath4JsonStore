package jp4js.benchmark.tests.xmarkpath;

import java.io.IOException;

import jp4js.benchmark.adapter.JaywayAdapter;
import jp4js.benchmark.adapter.JsonPathAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.XMarkPath;
import jp4js.benchmark.tests.JsonPathBenchMark;

public class JaywayBenchMark extends JsonPathBenchMark {
    @Override
    protected JsonPathAdapter adapter() {
        return new JaywayAdapter();
    }

    @Override
    protected DataSet dataSet() {
        XMarkPath dataset = new XMarkPath();
        try {
            dataset.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return dataset;
    }

    @Override
    protected String filename() {
        return "xmarkpath/jayway.txt";
    }

    public static void main(String[] args) {
        JaywayBenchMark benchMark = new JaywayBenchMark();
        benchMark.run();
    }
}
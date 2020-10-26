package jp4js.benchmark.tests.random;

import java.io.IOException;

import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.adapter.TreeMataAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.Random;
import jp4js.benchmark.tests.TplBenchMark;

public class TreeMataBenchMark extends TplBenchMark {
    @Override
    protected TplAdapter adapter() {
        return new TreeMataAdapter();
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
        return "random/treemata.txt";
    }

    public static void main(String[] args) {
        TreeMataBenchMark benchMark = new TreeMataBenchMark();
        benchMark.run();
    }
}
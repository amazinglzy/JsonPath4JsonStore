package jp4js.benchmark.tests.xmarkpath;

import java.io.IOException;

import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.adapter.TreeMataAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.XMarkPath;
import jp4js.benchmark.tests.TplBenchMark;

public class TreeMataBenchMark extends TplBenchMark {
    @Override
    protected TplAdapter adapter() {
        return new TreeMataAdapter();
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
        return "xmarkpath/treemata.txt";
    }

    public static void main(String[] args) {
        TreeMataBenchMark benchMark = new TreeMataBenchMark();
        benchMark.run();
    }
}
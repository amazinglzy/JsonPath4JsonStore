package jp4js.benchmark.tests.random;

import java.io.IOException;

import jp4js.benchmark.adapter.FullScanAdapter;
import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.Random;
import jp4js.benchmark.tests.TplBenchMark;

public class FullScanBenchMark extends TplBenchMark {
    @Override
    protected TplAdapter adapter() {
        return new FullScanAdapter();
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
        return "random/fullscan.txt";
    }

    public static void main(String[] args) {
        FullScanBenchMark benchMark = new FullScanBenchMark();
        benchMark.run();
    }
}
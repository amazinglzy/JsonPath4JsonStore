package jp4js.benchmark.tests.mge;

import java.io.IOException;

import jp4js.benchmark.adapter.FullScanAdapter;
import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.MGETemplate;
import jp4js.benchmark.tests.TplBenchMark;

public class FullScanBenchMark extends TplBenchMark {
    @Override
    protected TplAdapter adapter() {
        return new FullScanAdapter();
    }

    @Override
    protected DataSet dataSet() {
        MGETemplate dataset = new MGETemplate();
        try {
            dataset.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return dataset;
    }

    @Override
    protected String filename() {
        return "mge/fullscan.txt";
    }

    public static void main(String[] args) {
        FullScanBenchMark benchMark = new FullScanBenchMark();
        benchMark.run();
    }
}
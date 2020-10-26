package jp4js.benchmark.tests.xmarkstructure;

import java.io.IOException;

import jp4js.benchmark.adapter.RegionScanAdatper;
import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.XMarkStructure;
import jp4js.benchmark.tests.TplBenchMark;

public class RegionScanBenchMark extends TplBenchMark {
    @Override
    protected TplAdapter adapter() {
        return new RegionScanAdatper();
    }

    @Override
    protected DataSet dataSet() {
        XMarkStructure dataset = new XMarkStructure();
        try {
            dataset.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return dataset;
    }

    @Override
    protected String filename() {
        return "xmarkstructure/regionscan.txt";
    }

    public static void main(String[] args) {
        RegionScanBenchMark benchMark = new RegionScanBenchMark();
        benchMark.run();
    }
}
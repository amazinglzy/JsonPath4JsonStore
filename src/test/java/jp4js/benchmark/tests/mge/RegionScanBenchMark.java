package jp4js.benchmark.tests.mge;

import java.io.IOException;

import jp4js.benchmark.adapter.RegionScanAdatper;
import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.MGETemplate;
import jp4js.benchmark.tests.TplBenchMark;

public class RegionScanBenchMark extends TplBenchMark {
    @Override
    protected TplAdapter adapter() {
        return new RegionScanAdatper();
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
        return "mge/regionscan.txt";
    }

    public static void main(String[] args) {
        RegionScanBenchMark benchMark = new RegionScanBenchMark();
        benchMark.run();
    }
}
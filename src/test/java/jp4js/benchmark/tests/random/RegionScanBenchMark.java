package jp4js.benchmark.tests.random;

import java.io.IOException;

import jp4js.benchmark.adapter.RegionScanAdatper;
import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.dataset.Random;
import jp4js.benchmark.tests.TplBenchMark;

public class RegionScanBenchMark extends TplBenchMark {
    @Override
    protected TplAdapter adapter() {
        return new RegionScanAdatper();
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
        return "random/regionscan.txt";
    }

    public static void main(String[] args) {
        RegionScanBenchMark benchMark = new RegionScanBenchMark();
        benchMark.run();
    }
}
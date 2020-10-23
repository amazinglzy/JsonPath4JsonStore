package jp4js.benchmark;

import java.io.IOException;

import jp4js.benchmark.adapter.TreeMataAdapter;
import jp4js.benchmark.dataset.Random;
import jp4js.benchmark.runner.TplRunner;

public class TreeMataBenchMark {
    public static void main(String args[]) {
        // run workdir in the project
        Random random = new Random();
        try {
            random.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        TreeMataAdapter adapter = new TreeMataAdapter();
        adapter.index(random.data(), random.configuration());

        System.out.println("Load And Index Data");

        for (int i = 0; i < random.querySize(); i ++) {
            TplRunner runner = new TplRunner(adapter, random.tplQuery(i));
            runner.begin();
            for (int j = 0; j < 100; j++) {
                runner.test();
            }
            runner.end();

            System.out.println("Time: " + runner.average() + ", Results: " +
                               runner.results().toString());
        }
    }
}

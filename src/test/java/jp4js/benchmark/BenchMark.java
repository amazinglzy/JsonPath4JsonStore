package jp4js.benchmark;

import java.io.IOException;

import jp4js.benchmark.adapter.JaywayAdapter;
import jp4js.benchmark.dataset.Random;
import jp4js.benchmark.runner.JaywayRunner;

public class BenchMark {
    public static void main(String args[]) {
        // run workdir in the project
        Random random = new Random();
        try {
            random.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        JaywayAdapter adapter = new JaywayAdapter();
        adapter.index(random.data(), random.configuration());

        System.out.println("Load And Index Data");

        for (int i = 0; i < random.querySize(); i ++) {
            JaywayRunner runner = new JaywayRunner(adapter, random.jsonPathQuery(i));
            runner.begin();
            for (int j = 0; j < 10; j++) {
                runner.test();
            }
            runner.end();

            System.out.println(runner.average());
        }
    }
}

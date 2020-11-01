package jp4js.benchmark.tests.random;

import java.io.IOException;

import jp4js.benchmark.adapter.JaywayAdapter;
import jp4js.benchmark.dataset.Random;

public class DebugJaywayBenchMark {
    public static void main(String args[]) {
        Random random = new Random();
        try {
            random.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        JaywayAdapter adapter = new JaywayAdapter();

        adapter.index(random.data(), random.configuration());

        for (int i = 0; i < random.querySize(); ++ i) {
            long begin = System.currentTimeMillis();
            for (int num = 0; num < 10; ++ num) {
                adapter.query(random.jsonPathQuery(i));
            }

            long duration = System.currentTimeMillis() - begin;
            System.out.println(duration/10);
        }
    }
}

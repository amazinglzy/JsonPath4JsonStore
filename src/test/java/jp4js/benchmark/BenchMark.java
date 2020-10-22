package jp4js.benchmark;

import java.io.IOException;

import jp4js.benchmark.adapter.JaywayAdapter;
import jp4js.benchmark.dataset.Random;

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
            System.out.println(adapter.query(random.jsonPathQuery(i)));
        }
    }
}

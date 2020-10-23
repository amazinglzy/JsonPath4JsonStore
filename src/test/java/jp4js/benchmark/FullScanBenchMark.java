package jp4js.benchmark;

import java.io.IOException;
import java.util.Scanner;

import jp4js.benchmark.adapter.FullScanAdapter;
import jp4js.benchmark.dataset.Random;

public class FullScanBenchMark {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Random random = new Random();
        try {
            random.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        FullScanAdapter adapter = new FullScanAdapter();
        adapter.index(random.data(), random.configuration());

        System.out.print("Finish Index, Press Enter to Continue");
        scan.nextLine();

        for (int i = 0; i < random.querySize(); i ++) {
            long start = System.currentTimeMillis();
            int results = adapter.query(random.tplQuery(i));
            int num = 10;
            for (int j = 0; j < num - 1; j ++) {
                adapter.query(random.tplQuery(i));
            }
            long interval = System.currentTimeMillis() - start;
            System.out.printf("%d %d\n", results, interval / num);
        }
        scan.close();
    }
}

package jp4js.benchmark;

import java.io.IOException;
import java.util.Scanner;

import jp4js.benchmark.adapter.FullScanAdapter;
import jp4js.benchmark.dataset.Random;

public class FullScanBenchMark {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); String line;

        Random random = new Random();
        try {
            random.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        FullScanAdapter adapter = new FullScanAdapter();
        adapter.index(random.data(), random.configuration());

        System.out.println("Finish Index, Press Enter to Continue");
        line = scan.nextLine();

        for (int i = 0; i < random.querySize(); i ++) {
            System.out.println(adapter.query(random.tplQuery(i)));
        }
        scan.close();
    }
}

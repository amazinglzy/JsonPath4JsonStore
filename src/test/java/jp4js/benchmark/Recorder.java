package jp4js.benchmark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jp4js.benchmark.runner.Runner;

public class Recorder {
    private List<Runner> runners;
    private List<List<Long>> data;

    public Recorder() {
        this.runners = new LinkedList<>();
    }

    public void addRunnerGroup(List<Runner> runner) {
        this.runners.addAll(runner);
    }

    public void record(int num) {
        this.data = new ArrayList<>();
        List<Long> row = new ArrayList<>();
        this.data.add(row);
        for (Runner runner: runners) {
            runner.begin();
            for (int k = 0; k < num; k ++) {
                runner.test();
            }
            runner.end();
            row.add(runner.average());
        }

        for (Runner runner: this.runners) {
            System.out.println(runner.results());
        }
    }

    public void save(String filename) throws IOException {
        File file = new File("benchmark/output", filename);
        if (!file.exists()) {
            file.createNewFile();
        }

        Writer w = new FileWriter(file);
        for (List<Long> row: data) {
            w.write(row.toString());
        }
        w.close();
    }
}

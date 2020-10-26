package jp4js.benchmark.tests;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import jp4js.benchmark.Recorder;
import jp4js.benchmark.adapter.TplAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.runner.TplRunner;

public abstract class TplBenchMark {

    protected abstract TplAdapter adapter();
    protected abstract DataSet dataSet();
    protected abstract String filename();

    public void run() {
        TplAdapter adapter = adapter();
        DataSet dataset = dataSet();

        adapter.index(dataset.data(), dataset.configuration());
        
        Recorder recorder = new Recorder();
        recorder.addRunnerGroup(new LinkedList<>() {{
            for (int i = 0; i < dataset.querySize(); i ++) {
                add(new TplRunner(adapter, dataset.tplQuery(i)));
            }
        }});
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Finish Preparation, Press Enter to Continue: ");
        scan.nextLine();
        scan.close();

        recorder.record(5);
        try {
            recorder.save(filename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

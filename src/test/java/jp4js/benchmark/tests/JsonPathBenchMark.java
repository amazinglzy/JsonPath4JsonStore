package jp4js.benchmark.tests;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import jp4js.benchmark.Recorder;
import jp4js.benchmark.adapter.JsonPathAdapter;
import jp4js.benchmark.dataset.DataSet;
import jp4js.benchmark.runner.JaywayRunner;

public abstract class JsonPathBenchMark {

    protected abstract JsonPathAdapter adapter();
    protected abstract DataSet dataSet();
    protected abstract String filename();

    public void run() {
        JsonPathAdapter adapter = adapter();
        DataSet dataset = dataSet();

        adapter.index(dataset.data(), dataset.configuration());
        
        Recorder recorder = new Recorder();
        recorder.addRunnerGroup(new LinkedList<>() {{
            for (int i = 0; i < dataset.querySize(); i ++) {
                add(new JaywayRunner(adapter, dataset.jsonPathQuery(i)));
            }
        }});
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Finish Preparation, Press Enter to Continue: ");
        scan.nextLine();
        scan.close();

        recorder.record(20);
        try {
            recorder.save(filename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

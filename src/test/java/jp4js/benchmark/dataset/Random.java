package jp4js.benchmark.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.op.structure.StructureList;

public class Random implements DataSet {
    private Object data;
    private Configuration configuration;
    private String[] jsonPathQuery;
    private StructureList[] tplQuery;

    public Random() {
        this.data = null;
        this.configuration = Configuration.defaultConfiguration();
        this.jsonPathQuery = new String[] {
            "$[*]..a",
            "$[*]..a..a",
            "$[*]..a..a..a",
            "$[*]..a..a..a..a",
            "$[*]..a..a..a..a..a",
            "$[*]..a..a..a..a..a..a",
            "$[*]..a..a..a..a..a..a..a",
            "$[*]..a..a..a..a..a..a..a..a"
        };

        this.tplQuery = new StructureList[] {

        };
    }

    public void load() throws IOException {
        File file = new File("benchmark/random.json");
        InputStream is = new FileInputStream(file);
        this.data = this.configuration.jsonProvider().parse(is, "utf-8");
    }

    @Override
    public Object data() {
        return this.data;
    }

    @Override
    public Configuration configuration() {
        return this.configuration;
    }

    @Override
    public int querySize() {
        return this.jsonPathQuery.length;
    }

    @Override
    public String jsonPathQuery(int i) {
        return this.jsonPathQuery[i];
    }

    @Override
    public StructureList tplQuery(int i) {
        return this.tplQuery[i];
    }
}

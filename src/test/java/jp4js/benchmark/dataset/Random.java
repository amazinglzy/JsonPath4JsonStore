package jp4js.benchmark.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.operator.structure.StructureList;
import jp4js.algebra.operator.structure.StructureListBuilder;
import jp4js.algebra.operator.structure.StructureRelation;

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
            "$[*]..a..b",
            "$[*]..a..b..c",
            "$[*]..a..b..c..d",
            "$[*]..a..b..c..d..e",
            "$[*]..a..b..c..d..e..f",
            "$[*]..a..b..c..d..e..f..g",
            "$[*]..a..b..c..d..e..f..g..h"
        };

        this.tplQuery = new StructureList[] {
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                addStep(StructureRelation.AD, "b");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                addStep(StructureRelation.AD, "b");
                addStep(StructureRelation.AD, "c");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                addStep(StructureRelation.AD, "b");
                addStep(StructureRelation.AD, "c");
                addStep(StructureRelation.AD, "d");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                addStep(StructureRelation.AD, "b");
                addStep(StructureRelation.AD, "c");
                addStep(StructureRelation.AD, "d");
                addStep(StructureRelation.AD, "e");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                addStep(StructureRelation.AD, "b");
                addStep(StructureRelation.AD, "c");
                addStep(StructureRelation.AD, "d");
                addStep(StructureRelation.AD, "e");
                addStep(StructureRelation.AD, "f");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                addStep(StructureRelation.AD, "b");
                addStep(StructureRelation.AD, "c");
                addStep(StructureRelation.AD, "d");
                addStep(StructureRelation.AD, "e");
                addStep(StructureRelation.AD, "f");
                addStep(StructureRelation.AD, "g");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.AD, "a");
                addStep(StructureRelation.AD, "b");
                addStep(StructureRelation.AD, "c");
                addStep(StructureRelation.AD, "d");
                addStep(StructureRelation.AD, "e");
                addStep(StructureRelation.AD, "f");
                addStep(StructureRelation.AD, "g");
                addStep(StructureRelation.AD, "h");
                enter().exit();
            }}.build(),
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

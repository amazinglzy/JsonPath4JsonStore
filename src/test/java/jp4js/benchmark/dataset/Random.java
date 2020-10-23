package jp4js.benchmark.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.op.structure.StructureListBuilder;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;

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
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 1; i++) {
                    addStep(StructureRelation.AD, "a");
                }
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 2; i++) {
                    addStep(StructureRelation.AD, "a");
                }
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 3; i++) {
                    addStep(StructureRelation.AD, "a");
                }
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 4; i++) {
                    addStep(StructureRelation.AD, "a");
                }
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 5; i++) {
                    addStep(StructureRelation.AD, "a");
                }
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 6; i++) {
                    addStep(StructureRelation.AD, "a");
                }
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 7; i++) {
                    addStep(StructureRelation.AD, "a");
                }
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "*");
                for (int i = 0; i < 8; i++) {
                    addStep(StructureRelation.AD, "a");
                }
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

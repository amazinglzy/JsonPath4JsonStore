package jp4js.benchmark.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.operator.structure.StructureList;
import jp4js.algebra.operator.structure.StructureListBuilder;
import jp4js.algebra.operator.structure.StructureRelation;
import jp4js.algebra.operator.structure.StructureSteps;

public class XMarkStructure implements DataSet {
    private Object data;
    private Configuration configuration;
    private String[] jsonPathQuery;
    private StructureList[] tplQuery;

    public XMarkStructure() {
        this.data = null;
        this.configuration = Configuration.defaultConfiguration();
        this.jsonPathQuery = new String[] {};

        this.tplQuery = new StructureList[] {
            new StructureListBuilder() {{
                addSteps(new StructureSteps() {{
                    addStep(StructureRelation.PC, "people"); addStep(StructureRelation.PC, "*"); 
                }});
                addStep(StructureRelation.PC, "id");
                enter().exit();
                addStep(StructureRelation.PC, "name");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addSteps(new StructureSteps() {{
                    addStep(StructureRelation.PC, "open_auctions"); addStep(StructureRelation.PC, "*"); 
                }});
                addStep(StructureRelation.PC, "reserve");
                enter().exit();
                enter()
                    .addSteps(new StructureSteps() {{
                        addStep(StructureRelation.PC, "bidder");
                        addStep(StructureRelation.PC, "*");
                        addStep(StructureRelation.PC, "personref");
                    }})
                .exit();
            }}.build(),
            new StructureListBuilder() {{
                addSteps(new StructureSteps() {{
                    addStep(StructureRelation.AD, "items"); 
                    addStep(StructureRelation.PC, "*");
                }});
                addStep(StructureRelation.PC, "id");
                enter().exit();
            }}.build(),
        };
    }

    public void load() throws IOException {
        File file = new File("benchmark/xmark.json");
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
        return this.tplQuery.length;
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

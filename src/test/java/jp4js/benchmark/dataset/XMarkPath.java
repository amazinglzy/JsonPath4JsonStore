package jp4js.benchmark.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.op.structure.StructureListBuilder;
import jp4js.algebra.op.structure.StructureRelation;

public class XMarkPath implements DataSet {
    private Object data;
    private Configuration configuration;
    private String[] jsonPathQuery;
    private StructureList[] tplQuery;

    public XMarkPath() {
        this.data = null;
        this.configuration = Configuration.defaultConfiguration();
        this.jsonPathQuery = new String[] {
            "$.open_auctions[*].bidder[0].increase",
            "$.people[*].id",
            "$.region[*].items[*].description",
            "$.people..interst",
            "$.region..location",
            "$.region..parlist..parlist"
        };

        this.tplQuery = new StructureList[] {
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "open_auctions");
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.PC, "bidder");
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.PC, "increase");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "people");
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.PC, "id");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "region");
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.PC, "items");
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.PC, "description");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "people");
                addStep(StructureRelation.AD, "interest");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "region");
                addStep(StructureRelation.AD, "location");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addStep(StructureRelation.PC, "region");
                addStep(StructureRelation.AD, "parlist");
                addStep(StructureRelation.AD, "parlist");
                enter().exit();
            }}.build()
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

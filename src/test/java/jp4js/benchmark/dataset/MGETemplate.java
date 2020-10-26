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

public class MGETemplate implements DataSet {
    private Object data;
    private Configuration configuration;
    private String[] jsonPathQuery;
    private StructureList[] tplQuery;

    public MGETemplate() {
        this.data = null;
        this.configuration = Configuration.defaultConfiguration();
        this.jsonPathQuery = new String[] {};

        this.tplQuery = new StructureList[] {
            new StructureListBuilder() {{
                addSteps(new StructureSteps() {{
                    addStep(StructureRelation.PC, "*");
                    addStep(StructureRelation.PC, "数据收集与审核"); 
                    addStep(StructureRelation.PC, "*"); 
                }});
                addStep(StructureRelation.PC, "数据收集人员");
                enter().exit();
                addStep(StructureRelation.PC, "数据审核人员");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addSteps(new StructureSteps() {{
                    addStep(StructureRelation.PC, "*"); 
                    addStep(StructureRelation.PC, "实验条件");
                    addStep(StructureRelation.PC, "*"); 
                }});
                addStep(StructureRelation.PC, "实验条件");
                enter().exit();
                addStep(StructureRelation.PC, "参数名");
                enter().exit();
                addStep(StructureRelation.PC, "参数值");
                enter().exit();
                addStep(StructureRelation.PC, "单位");
                enter().exit();
            }}.build(),
            new StructureListBuilder() {{
                addSteps(new StructureSteps() {{
                    addStep(StructureRelation.PC, "*");
                }});
                addStep(StructureRelation.AD, "化学元素");
                enter().exit();
                addStep(StructureRelation.PC, "实验条件");
                enter()
                    .addSteps(new StructureSteps() {{ addStep(StructureRelation.PC, "*"); }})
                    .addStep(StructureRelation.PC, "条件名称")
                    .enter().exit()
                    .addStep(StructureRelation.PC, "参数名")
                    .enter().exit()
                    .addStep(StructureRelation.PC, "参数值")
                    .enter().exit()
                    .addStep(StructureRelation.PC, "单位")
                    .enter().exit()
                .exit();
            }}.build()
        };
    }

    public void load() throws IOException {
        File file = new File("benchmark/mge/merged.json");
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

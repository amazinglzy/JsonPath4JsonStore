package jp4js.storage.dewey;


import jp4js.algebra.Domain;
import jp4js.algebra.Scalar;
import jp4js.algebra.operator.structure.StructureList;
import jp4js.algebra.operator.structure.StructureListBuilder;
import jp4js.algebra.operator.structure.StructureRelation;
import jp4js.algebra.operator.structure.StructureSteps;
import jp4js.algebra.tpl.AtomicValue;
import jp4js.algebra.tpl.NestedData;
import jp4js.data.BaseDataSuite;
import jp4js.data.Goessener;
import jp4js.data.JsonArray;
import jp4js.data.JsonArrayMulti;
import jp4js.data.JsonPathWebsite;
import jp4js.data.NestedFieldname;
import jp4js.data.NestedSameFieldname;
import jp4js.data.XMarkSample;
import jp4js.utils.algebra.Trans;
import jp4js.utils.iter.CompareIter;
import jp4js.utils.iter.Iter;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.jayway.jsonpath.Configuration;



public class DeweyIndexTest {

    private final DeweyIndex basicIndex;
    {
        String s = "{'a': [1, 2, 3], 'b': {'a': 8}}";
        Configuration configuration = Configuration.defaultConfiguration();
        Object json = configuration.jsonProvider().parse(s);

        Domain.Instance ins = Trans.fromJSON(json, configuration);
        basicIndex = DeweyIndex.build(ins);
    }
    
    @Test
    public void basic01_() {

        StructureSteps steps = new StructureSteps();
        steps.addStep(StructureRelation.PC, "a");
        steps.addStep(0, 1);

        Iter<IndexNode> iter = basicIndex.query(steps);

        CompareIter.assertEqual(
            iter,
            new LinkedList<Domain.Instance>(
                Arrays.asList(
                    Scalar.createDInt(1)
                )
            ),
            (IndexNode node, Domain.Instance val) -> {
                if (node.data instanceof AtomicValue) {
                    AtomicValue av = (AtomicValue)node.data;
                    return av.data().equals(val);
                }
                return false;
            }
        );
    }

    @Test
    public void basic02_() {
        StructureSteps steps = new StructureSteps();
        steps.addStep(StructureRelation.AD, "a");

        Iter<IndexNode> iter = basicIndex.query(steps);
        CompareIter.assertEqual(
            iter,
            new LinkedList<Domain.Instance>(
                Arrays.asList(
                    Scalar.createDListValues(
                        Arrays.asList(
                            Scalar.createDInt(1),
                            Scalar.createDInt(2),
                            Scalar.createDInt(3)
                        )
                    ),
                    Scalar.createDInt(8)
                )
            ),
            (IndexNode node, Domain.Instance val) -> {
                if (node.data instanceof AtomicValue) {
                    AtomicValue av = (AtomicValue)node.data;
                    return av.data().equals(val);
                }
                return false;
            }
        );

    }

    @Test
    public void basic03_DataSuite_Sample() {
        Goessener suite = new Goessener();
        Domain.Instance instance = suite.instance();
        DeweyIndex index = DeweyIndex.build(instance);
        StructureList lst = suite.query(0);
        List<NestedData> results = index.query(lst);

        assertThat(results.toString()).isEqualTo(
            "[[(\"Nigel Rees\"), (\"Evelyn Waugh\"), (\"Herman Melville\"), (\"J. R. R. Tolkien\")]]"
        );
    }

    @Test
    public void basic04_DataSuite_Goessener() {
        forDataSuite(new Goessener());
    }

    @Test
    public void basic05_DataSuite_JsonArray() {
        forDataSuite(new JsonArray());
    }

    @Test
    public void basic06_DataSuite_JsonArrayMulti() {
        forDataSuite(new JsonArrayMulti());
    }

    @Test
    public void basic07_DataSuite_JsonPathWebsite() {
        forDataSuite(new JsonPathWebsite());
    }

    @Test
    public void basic08_DataSuite_NestedFieldname() {
        forDataSuite(new NestedFieldname());
    }

    @Test
    public void basic08_IncorrectResult_NestedFieldname() {
        NestedFieldname suite = new NestedFieldname();
        Domain.Instance instance = suite.instance();
        DeweyIndex index = DeweyIndex.build(instance);
        StructureList lst = suite.query(1);
        List<NestedData> results = index.query(lst);

        assertThat(results.toString()).isEqualTo(
            "[[([3]), (\"field2-field1\")]]"
        );

    }

    @Test
    public void basic09_DataSuite_NestedSameFieldname() {
        forDataSuite(new NestedSameFieldname());
    }

    @Test
    public void basic10_DataSuite_XMarkSample() {
        forDataSuite(new XMarkSample());
    }

    @Test
    public void basic10_IncorrectResult_XMarkSample() {
        XMarkSample suite = new XMarkSample();
        Domain.Instance instance = suite.instance();
        DeweyIndex index = DeweyIndex.build(instance);
        StructureList lst = suite.query(1);
        List<NestedData> results = index.query(lst);

        System.out.println(results.toString());

        assertThat(results.toString()).isEqualTo(
            "[[(\"United States\"), (\"United States\"), (\"Denmark\"), (\"Uzbekistan\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\")]]"
        );
    }

    @Test
    public void basic11_MGETemplateSample() {
        ClassLoader loader = getClass().getClassLoader();
        File mgeFile = new File(loader.getResource("mge_sample.json").getFile());
        String json;
        try {
            json = new String(Files.readAllBytes(mgeFile.toPath()));
        } catch (IOException e) {
            assert(false);
            return;
        }

        Configuration configuration = Configuration.defaultConfiguration();
        Domain.Instance ins = Trans.fromJSON(
            configuration.jsonProvider().parse(json), 
            configuration);
        
        DeweyIndex index = DeweyIndex.build(ins);

        StructureList lst = new StructureListBuilder() {{
            addSteps(new StructureSteps() {{
                addStep(StructureRelation.PC, "*");
                addStep(StructureRelation.PC, "content");
                addStep(StructureRelation.PC, "数据收集与审核"); 
                addStep(StructureRelation.PC, "*"); 
            }});
            addStep(StructureRelation.PC, "数据收集人员");
            enter().exit();
            addStep(StructureRelation.PC, "数据审核人员");
            enter().exit();
        }}.build();
        
        System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo("SELECT { .数据收集人员: SELECT {  }, .数据审核人员: SELECT {  } } NESTEDBY [.*.content.数据收集与审核.*]");
        List<NestedData> res = index.query(lst);
        System.out.println(res.toString());
        assertThat(res.toString()).isEqualTo("[[(\"Bin Xu\", \"Haiqing Yin\"), (\"LALALA\", \"Haiqing Yin\")]]");
    }


    private void forDataSuite(BaseDataSuite suite) {
        DeweyIndex index = DeweyIndex.build(suite.instance());
        for (int i = 0; i < suite.querySize(); ++ i) {
            StructureList lst = suite.query(i);
            List<NestedData> results = index.query(lst);
            System.out.println(results.toString());
            System.out.println(suite.res()[i]);

            if (i < suite.res().length) {
                assertThat(results.toString()).isEqualTo(suite.res()[i]);
            }
        }
    }
}

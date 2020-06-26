package jp4js.nf2.tpl;

import jp4js.nf2.Scalar;
import jp4js.nf2.DType;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

public class NestedRelationTest {

    @Test
    public void testBasicUsageCreation01() {
        Template rel = new TemplateBuilder()
            .put("field1", Scalar.dString)
            .put("field2", Scalar.dInt)
            .put("field3", Scalar.dDouble)
            .enter("field4")
                .put("field1", Scalar.dString)
                .put("field2", Scalar.dDouble)
            .exit()
            .build();
        System.out.println(rel.toString());
        assertThat(rel.toString()).isEqualTo(
            "[field1(DString), field2(DInt), field3(DDouble), field4[field1(DString), field2(DDouble)]]"
        );
    }

    @Test
    public void testBasicUsageBuildInstance01() {
        RepeatableDocumentSet ins = new RepeatableDocumentSet(
            new LinkedList<>() {{
                add(
                    new SingularDocumentSet(
                        new DType.Instance[]{
                            Scalar.createDInt(20),
                            Scalar.createDString("Alice")
                        }
                    )
                );
                add(
                    new SingularDocumentSet(
                        new DType.Instance[] {
                            Scalar.createDInt(10),
                            Scalar.createDString("Alice")
                        }
                    )
                );
            }}
        );
        assertThat(ins.toString()).isEqualTo(
            "[(20, \"Alice\"), (10, \"Alice\")]"
        );
    }

    @Test
    public void testBasicUsageBuildInstance02() {
        RepeatableDocumentSet instance = new RepeatableDocumentSet(
            new LinkedList<>() {{
                add(
                    new SingularDocumentSet(
                        new DType.Instance[] {
                            Scalar.createDInt(23),
                            new RepeatableDocumentSet(
                                new LinkedList<>() {{
                                    add(new SingularDocumentSet(new DType.Instance[]{
                                        Scalar.createDString("English"),
                                        Scalar.createDInt(100)
                                    }));
                                    add(new SingularDocumentSet(new DType.Instance[]{
                                        Scalar.createDString("Chinese"),
                                        Scalar.createDInt(10)
                                    }));
                                }}
                            ),
                            Scalar.createDString("L")
                        }
                    )
                );
            }}
        );
        // System.out.println(instance.toString());
        assertThat(instance.toString()).isEqualTo(
            "[(23, [(\"English\", 100), (\"Chinese\", 10)], \"L\")]"
        );
    }
}

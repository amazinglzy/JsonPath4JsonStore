package jp4js.algebra.tpl;

import jp4js.algebra.Scalar;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

public class DHeaderTest {

    @Test
    public void testBasicUsageCreation01() {
        DHeader rel = new DHeaderBuilder()
            .put("field1", Scalar.dString)
            .put("field2", Scalar.dString)
            .put("field3", Scalar.dString)
            .enter("field4", 1)
                .put("field1", Scalar.dString)
                .put("field2", Scalar.dString)
            .exit()
            .build();
        System.out.println(rel.toString());
        assertThat(rel.toString()).isEqualTo(
            "[(field1, field2, field3, field4[(field1, field2)])]"
        );
    }

    @Test
    public void testBasicUsageBuildInstance01() {
        ListTuple ins = new ListTuple(
            new LinkedList<>() {{
                add(
                    new Tuple(
                        new NestedData[] {
                            new Tuple(Scalar.createDInt(20)),
                            new Tuple(Scalar.createDString("Alice"))
                        }
                    )
                );
                add(
                    new Tuple(
                        new NestedData[] {
                            new Tuple(Scalar.createDInt(10)),
                            new Tuple(Scalar.createDString("Alice"))
                        }
                    )
                );
            }}
        );
        System.out.println(ins.toString());
        assertThat(ins.toString()).isEqualTo(
            "[(20, \"Alice\"), (10, \"Alice\")]"
        );
    }

    @Test
    public void testBasicUsageBuildInstance02() {
        ListTuple instance = new ListTuple(
            new LinkedList<>() {{
                add(
                    new Tuple(
                        new NestedData[] {
                            new Tuple(Scalar.createDInt(23)),
                            new ListTuple(
                                new LinkedList<>() {{
                                    add(new Tuple(
                                        new NestedData[]{
                                            new Tuple(Scalar.createDString("English")),
                                            new Tuple(Scalar.createDInt(100))
                                        }
                                    ));
                                    add(new Tuple(
                                        new NestedData[]{
                                            new Tuple(Scalar.createDString("Chinese")),
                                            new Tuple(Scalar.createDInt(10))
                                        }
                                    ));
                                }}
                            ),
                            new Tuple(Scalar.createDString("L"))
                        }
                    )
                );
            }}
        );
        System.out.println(instance.toString());
        assertThat(instance.toString()).isEqualTo(
            "[(23, [(\"English\", 100), (\"Chinese\", 10)], \"L\")]"
        );
    }
}

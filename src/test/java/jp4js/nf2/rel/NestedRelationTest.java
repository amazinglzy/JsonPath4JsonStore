package jp4js.nf2.rel;

import static jp4js.nf2.rel.NestedRelationSample.*;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NestedRelationTest {

    @Test
    public void testBasicUsageCreation01() {
        NestedRelation rel = new NestedRelationBuilder()
            .put("field1", BasicType.dString)
            .put("field2", BasicType.dInt)
            .put("field3", BasicType.dDouble)
            .enter("field4")
                .put("field1", BasicType.dString)
                .put("field2", BasicType.dDouble)
            .exit()
            .build();
        assertThat(rel.toString()).isEqualTo(
            "field1(DString), field2(DInt), field3(DDouble), field4(field1(DString), field2(DDouble))"
        );
    }

    @Test
    public void testBasicUsageBuildInstance01() {
        NestedRelation.Instance ins = 
        flatRel0.builder()
            .begin()
                .put("age", BasicType.createDInt(20))
                .put("name", BasicType.createDString("Alice"))
            .end()
            .begin()
                .put("age", BasicType.createDInt(10))
                .put("name", BasicType.createDString("Alice"))
            .end()
            .build();
        assertThat(ins.toString()).isEqualTo(
            "[(20, \"Alice\"), (10, \"Alice\")]"
        );
    }

    @Test
    public void testBasicUsageBuildInstance02() {
        NestedRelation.Instance instance = 
            nestedRel0.builder()
                .begin()
                    .put("age", BasicType.createDInt(23))
                    .put("name", BasicType.createDString("L"))
                    .enter("courses")
                        .begin()
                            .put("course", BasicType.createDString("English"))
                            .put("score", BasicType.createDInt(100))
                        .end()
                        .begin()
                            .put("course", BasicType.createDString("Chinese"))
                            .put("score", BasicType.createDInt(10))
                        .end()
                    .exit()
                .end()
                .build();
            assertThat(instance.toString()).isEqualTo(
                "[(23, [(\"English\", 100), (\"Chinese\", 10)], \"L\")]"
            );
    }
}

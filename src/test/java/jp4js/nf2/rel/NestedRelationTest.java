package jp4js.nf2.rel;

import static jp4js.nf2.rel.NestedRelationSample.*;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NestedRelationTest {

    @Test
    public void testBasicUsageCreation01() {
        NestedRelation rel = new NestedRelationBuilder()
            .put("field1", Document.dString)
            .put("field2", Document.dInt)
            .put("field3", Document.dDouble)
            .enter("field4")
                .put("field1", Document.dString)
                .put("field2", Document.dDouble)
            .exit()
            .build();
        assertThat(rel.toString()).isEqualTo(
            "field1(DString), field2(DInt), field3(DDouble), field4(field1(DString), field2(DDouble))"
        );
    }

    @Test
    public void testBasicUsageBuildInstance01() {
        DocumentSetList ins = 
        flatRel0.builder()
            .begin()
                .put("age", Document.createDInt(20))
                .put("name", Document.createDString("Alice"))
            .end()
            .begin()
                .put("age", Document.createDInt(10))
                .put("name", Document.createDString("Alice"))
            .end()
            .build();
        assertThat(ins.toString()).isEqualTo(
            "[(20, \"Alice\"), (10, \"Alice\")]"
        );
    }

    @Test
    public void testBasicUsageBuildInstance02() {
        DocumentSetList instance = 
            nestedRel0.builder()
                .begin()
                    .put("age", Document.createDInt(23))
                    .put("name", Document.createDString("L"))
                    .enter("courses")
                        .begin()
                            .put("course", Document.createDString("English"))
                            .put("score", Document.createDInt(100))
                        .end()
                        .begin()
                            .put("course", Document.createDString("Chinese"))
                            .put("score", Document.createDInt(10))
                        .end()
                    .exit()
                .end()
                .build();
            assertThat(instance.toString()).isEqualTo(
                "[(23, [(\"English\", 100), (\"Chinese\", 10)], \"L\")]"
            );
    }
}

package jp4js.nf2.rel;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NestedRelationBuilderTest {

    @Test
    public void testNestedRelationBuilderFlat01() {
        NestedRelation relation = new NestedRelationBuilder()
            .put("age", Document.dInt)
            .put("name", Document.dString)
            .build();
        assertThat(relation.toString()).isEqualTo("age(DInt), name(DString)");
    }

    @Test
    public void testNestedRelationBuilderNested01() {
        NestedRelation relation = new NestedRelationBuilder()
            .put("age", Document.dInt)
            .put("name", Document.dString)
            .enter("courses")
                .put("course", Document.dString)
                .put("score", Document.dInt)
            .exit()
            .build();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo(
            "age(DInt), courses(course(DString), score(DInt)), name(DString)"
        );
    }

    @Test
    public void testNestedRelationBuilderNested02() {
        NestedRelation relation = new NestedRelationBuilder()
            .put("field1", Document.dString)
            .put("field2", Document.dInt)
            .put("field3", Document.dDouble)
            .enter("field4")
                .put("field1", Document.dString)
                .put("field2", Document.dDouble)
            .exit()
            .build();
        assertThat(relation.toString()).isEqualTo(
            "field1(DString), field2(DInt), field3(DDouble), field4(field1(DString), field2(DDouble))"
        );
    }
}
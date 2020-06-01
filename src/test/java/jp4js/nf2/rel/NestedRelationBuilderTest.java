package jp4js.nf2.rel;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NestedRelationBuilderTest {

    @Test
    public void testNestedRelationBuilderFlat01() {
        NestedRelation relation = new NestedRelationBuilder()
            .put("age", BasicType.dInt)
            .put("name", BasicType.dString)
            .build();
        assertThat(relation.toString()).isEqualTo("age(DInt), name(DString)");
    }

    @Test
    public void testNestedRelationBuilderNested01() {
        NestedRelation relation = new NestedRelationBuilder()
            .put("age", BasicType.dInt)
            .put("name", BasicType.dString)
            .enter()
                .put("course", BasicType.dString)
                .put("score", BasicType.dInt)
            .exit("courses")
            .build();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo(
            "age(DInt), courses(course(DString), score(DInt)), name(DString)"
        );
    }

    @Test
    public void testNestedRelationBuilderNested02() {
        NestedRelation relation = new NestedRelationBuilder()
            .put("field1", BasicType.dString)
            .put("field2", BasicType.dInt)
            .put("field3", BasicType.dDouble)
            .enter()
                .put("field1", BasicType.dString)
                .put("field2", BasicType.dDouble)
            .exit("field4")
            .build();
        assertThat(relation.toString()).isEqualTo(
            "field1(DString), field2(DInt), field3(DDouble), field4(field1(DString), field2(DDouble))"
        );
    }
}
package jp4js.algebra.tpl;

import org.junit.Test;

import jp4js.algebra.Scalar;

import static org.assertj.core.api.Assertions.assertThat;


public class DHeaderBuilderTest {

    @Test
    public void testNestedRelationBuilderFlat01() {
        DHeader relation = new DHeaderBuilder()
            .put("age", Scalar.dInt)
            .put("name", Scalar.dString)
            .build();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo("[(age, name)]");
    }

    @Test
    public void testNestedRelationBuilderNested01() {
        DHeader relation = new DHeaderBuilder()
            .put("age", Scalar.dInt)
            .put("name", Scalar.dString)
            .enter("courses", 1)
                .put("course", Scalar.dString)
                .put("score", Scalar.dInt)
            .exit()
            .build();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo(
            "[(age, courses[(course, score)], name)]"
        );
    }

    @Test
    public void testNestedRelationBuilderNested02() {
        DHeader relation = new DHeaderBuilder()
            .put("field1", Scalar.dString)
            .put("field2", Scalar.dString)
            .put("field3", Scalar.dString)
            .enter("field4", 1)
                .put("field1", Scalar.dString)
                .put("field2", Scalar.dString)
            .exit()
            .build();
        assertThat(relation.toString()).isEqualTo(
            "[(field1, field2, field3, field4[(field1, field2)])]"
        );
    }
}
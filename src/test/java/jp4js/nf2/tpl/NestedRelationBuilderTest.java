package jp4js.nf2.tpl;

import jp4js.nf2.Scalar;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NestedRelationBuilderTest {

    @Test
    public void testNestedRelationBuilderFlat01() {
        Template relation = new TemplateBuilder()
            .put("age", Scalar.dInt)
            .put("name", Scalar.dString)
            .build();
        assertThat(relation.toString()).isEqualTo("[age(DInt), name(DString)]");
    }

    @Test
    public void testNestedRelationBuilderNested01() {
        Template relation = new TemplateBuilder()
            .put("age", Scalar.dInt)
            .put("name", Scalar.dString)
            .enter("courses")
                .put("course", Scalar.dString)
                .put("score", Scalar.dInt)
            .exit()
            .build();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo(
            "[age(DInt), courses[course(DString), score(DInt)], name(DString)]"
        );
    }

    @Test
    public void testNestedRelationBuilderNested02() {
        Template relation = new TemplateBuilder()
            .put("field1", Scalar.dString)
            .put("field2", Scalar.dInt)
            .put("field3", Scalar.dDouble)
            .enter("field4")
                .put("field1", Scalar.dString)
                .put("field2", Scalar.dDouble)
            .exit()
            .build();
        assertThat(relation.toString()).isEqualTo(
            "[field1(DString), field2(DInt), field3(DDouble), field4[field1(DString), field2(DDouble)]]"
        );
    }
}
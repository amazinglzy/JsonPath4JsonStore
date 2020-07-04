package jp4js.nf2.tpl;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DHeaderBuilderTest {

    @Test
    public void testNestedRelationBuilderFlat01() {
        DHeader relation = new DHeaderBuilder()
            .put("age")
            .put("name")
            .build();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo("[age, name]");
    }

    @Test
    public void testNestedRelationBuilderNested01() {
        DHeader relation = new DHeaderBuilder()
            .put("age")
            .put("name")
            .enter("courses")
                .put("course")
                .put("score")
            .exit()
            .build();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo(
            "[age, courses[course, score], name]"
        );
    }

    @Test
    public void testNestedRelationBuilderNested02() {
        DHeader relation = new DHeaderBuilder()
            .put("field1")
            .put("field2")
            .put("field3")
            .enter("field4")
                .put("field1")
                .put("field2")
            .exit()
            .build();
        assertThat(relation.toString()).isEqualTo(
            "[field1, field2, field3, field4[field1, field2]]"
        );
    }
}
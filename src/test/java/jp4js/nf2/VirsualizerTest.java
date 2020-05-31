package jp4js.nf2;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VirsualizerTest {
    @Test
    public void testHeaderOnlyFlatRel0() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder().build();
        Virsualizer virsualizer = new Virsualizer(instance);
        
        assertThat(virsualizer.toString()).isEqualTo(
            " -------------- \n"+
            "| ----- ------ |\n"+
            "|| age | name ||\n"+
            "| ----- ------ |\n"+
            " -------------- "
        );
    }

    @Test
    public void testheaderOnlyNestedRel0() {
        NestedRelation.Instance instance = NestedRelationSample.nestedRel0.builder().build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------------------------- \n"+
            "| ----- -------------------- ------ |\n"+
            "|| age | ------------------ | name ||\n"+
            "||     || courses          ||      ||\n"+
            "||     | ------------------ |      ||\n"+
            "||     || -------- ------- ||      ||\n"+
            "||     ||| course | score |||      ||\n"+
            "||     || -------- ------- ||      ||\n"+
            "||     | ------------------ |      ||\n"+
            "| ----- -------------------- ------ |\n"+
            " ----------------------------------- "
        );
    }

    @Test
    public void testHeaderAndTuplesFlat0() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder()
            .begin()
                .put("name", BasicType.createDString("Alice"))
                .put("age", BasicType.createDInt(10))
            .end()
            .build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "|| age | name    ||\n" +
            "| ----- --------- |\n" +
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "|| 10  | \"Alice\" ||\n" +
            "| ----- --------- |\n" +
            " ----------------- "
        );
    }

    @Test
    public void testHeaderAndTupleFlat0ExistsNull() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder()
            .begin()
                .put("name", BasicType.createDString("Alice"))
            .end()
            .build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "|| age | name    ||\n" +
            "| ----- --------- |\n" +
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "||     | \"Alice\" ||\n" +
            "| ----- --------- |\n" +
            " ----------------- "
        );
    }
}
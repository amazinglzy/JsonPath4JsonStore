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
}
package jp4js.nf2;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VirsualizerTest {
    @Test
    public void testVirsualizerArrangementFlat01() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder().build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.arrangement().get("age").data()).isEqualTo(
            3 + 2
        );
        assertThat(virsualizer.arrangement().get("name").data()).isEqualTo(
            4 + 2
        );
    }

    @Test
    public void testVirsualizerArrangementNested01() {
        NestedRelation.Instance instance = NestedRelationSample.nestedRel0.builder().build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(
            ((Virsualizer.NestedRelationTypeArrangement)virsualizer.arrangement().get("courses")).get("course").data()
        ).isEqualTo(6 + 2);
        assertThat(
            ((Virsualizer.NestedRelationTypeArrangement)virsualizer.arrangement().get("courses")).data()
        ).isEqualTo(6 + 2 + 1 + 5 + 2);
    }

    @Test
    public void testVirsualizerHeaderOnly() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder().build();
        Virsualizer virsualizer = new Virsualizer(instance);
        
        assertThat(virsualizer.toString()).isEqualTo(
            " ----- ------ \n" +
            "| age | name |\n" +
            " ----- ------ "
        );
    }

}
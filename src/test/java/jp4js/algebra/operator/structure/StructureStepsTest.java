package jp4js.algebra.operator.structure;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StructureStepsTest {
    @Test
    public void basic01_() {
        StructureSteps steps = new StructureSteps();
        steps.addStep(StructureRelation.PC, "a");
        steps.addStep(StructureRelation.AD, "b");
        steps.addStep(StructureRelation.AD, "c");
        assertThat(steps.toString()).isEqualTo(".a..b..c");
    }
}
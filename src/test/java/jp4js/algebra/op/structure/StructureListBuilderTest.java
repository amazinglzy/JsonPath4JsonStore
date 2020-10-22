package jp4js.algebra.op.structure;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StructureListBuilderTest {
    @Test
    public void basic01_() {
        StructureListBuilder builder = new StructureListBuilder();
        builder.addSteps(
            new StructureSteps() {{
                addStep(StructureRelation.PC, "*"); }})
            .addStep(StructureRelation.PC, "f")
            .enter()
            .exit()
            .addStep(1, 5)
            .enter()
            .exit();

        StructureList lst = builder.build();
        // System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo("[[.*]:asy(.f:asy(), .{1,5}:asy())]");
    }

    @Test
    public void basic02_() {
        StructureListBuilder builder = new StructureListBuilder();
        builder.addSteps(
            new StructureSteps() {{
                addStep(StructureRelation.PC, "*"); }})
            .addStep(StructureRelation.PC, "f")
            .enter()
                .addStep(1, 5)
                .enter()
                .exit()
            .exit();

        StructureList lst = builder.build();
        // System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo("[[.*]:asy(.f:asy(.{1,5}:asy()))]");
    }
}

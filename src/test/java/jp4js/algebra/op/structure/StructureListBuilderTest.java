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
        System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo("SELECT { .f: SELECT {  }, .{1,5}: SELECT {  } } NESTEDBY [.*]");
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
        assertThat(lst.toString()).isEqualTo("SELECT { .f: SELECT { .{1,5}: SELECT {  } } } NESTEDBY [.*]");
    }

    @Test
    public void basic03_RandomExample() {
        StructureList lst = new StructureListBuilder() {{
            addSteps(new StructureSteps() {{addStep(StructureRelation.PC, "*");}});
            for (int i = 0; i < 2; i++) {
                addStep(StructureRelation.AD, "a");
            }
            enter().exit();
        }}.build();
        System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo("SELECT { ..a..a: SELECT {  } } NESTEDBY [.*]");
    }
}

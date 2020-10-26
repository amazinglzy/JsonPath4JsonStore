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

    @Test
    public void basic04_() {
        StructureList lst = new StructureListBuilder() {{
                addSteps(new StructureSteps() {{
                    addStep(StructureRelation.PC, "open_auctions"); addStep(StructureRelation.PC, "*"); 
                }});
                addStep(StructureRelation.PC, "reserve");
                enter().exit();
                enter()
                    .addSteps(new StructureSteps() {{
                        addStep(StructureRelation.PC, "bidder");
                        addStep(StructureRelation.PC, "*");
                        addStep(StructureRelation.PC, "personref");
                    }})
                .exit();
            }}.build();
        System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo("SELECT { .reserve: SELECT {  }, @: SELECT {  } NESTEDBY [.bidder.*.personref] } NESTEDBY [.open_auctions.*]");
    }

    @Test
    public void basic05_() {
        StructureList lst = new StructureListBuilder() {{
            addSteps(new StructureSteps() {{
                addStep(StructureRelation.PC, "*");
            }});
            addStep(StructureRelation.AD, "化学元素");
            enter().exit();
            addStep(StructureRelation.PC, "实验条件");
            enter()
                .addSteps(new StructureSteps() {{ addStep(StructureRelation.PC, "*"); }})
                .addStep(StructureRelation.PC, "条件名称")
                .enter().exit()
                .addStep(StructureRelation.PC, "参数名")
                .enter().exit()
                .addStep(StructureRelation.PC, "参数值")
                .enter().exit()
                .addStep(StructureRelation.PC, "单位")
                .enter().exit()
            .exit();
        }}.build();
        System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo("SELECT { ..化学元素: SELECT {  }, .实验条件: SELECT { .条件名称: SELECT {  }, .参数名: SELECT {  }, .参数值: SELECT {  }, .单位: SELECT {  } } NESTEDBY [.*] } NESTEDBY [.*]");
    }
}

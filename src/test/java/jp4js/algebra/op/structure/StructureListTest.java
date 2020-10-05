package jp4js.algebra.op.structure;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StructureListTest {
    @Test
    public void basic01_() {
        StructureList lst = new RepeatableSL(new StructureSteps(), new SingularSL(){{
            put("first", null, StructureRelation.AD);
            put("last", null, StructureRelation.AD);
            put("addr", new RepeatableSL(new StructureSteps(), new SingularSL()), StructureRelation.PC);
        }});
        // System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo(
            "[:asynomous(..first, ..last, .addr:[:asynomous()])]"
        );
    }

    @Test
    public void basic02_() {
        RepeatableSL lst1 = new RepeatableSL(new StructureSteps(), new SingularSL() {{
            put("first", null, StructureRelation.AD);
            put("last", null, StructureRelation.AD);
        }});
        SingularSL lst2 = new SingularSL(){{
            put("addr", new RepeatableSL(new StructureSteps(), new SingularSL()), StructureRelation.PC);
        }};
        lst1.mergeIn(lst2);
        System.out.println(lst1.toString());
        assertThat(lst1.toString()).isEqualTo(
            "[:asynomous(..first, ..last, .addr:[:asynomous()])]"
        );
    }
}
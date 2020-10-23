package jp4js.algebra.op.structure;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StructureListTest {
    @Test
    public void basic01_() {
        StructureList lst = new StructureList(new StructureSteps(), new StructureList(){{
            put("first", null, StructureRelation.AD);
            put("last", null, StructureRelation.AD);
            put("addr", new StructureList(new StructureSteps(), new StructureList()), StructureRelation.PC);
        }});
        System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo(
            "SELECT { ..first, ..last, .addr: SELECT {  } NESTEDBY [@] } NESTEDBY [@]"
        );
    }

    @Test
    public void basic02_() {
        StructureList lst1 = new StructureList(new StructureSteps(), new StructureList() {{
            put("first", null, StructureRelation.AD);
            put("last", null, StructureRelation.AD);
        }});
        StructureList lst2 = new StructureList(){{
            put("addr", new StructureList(new StructureSteps(), new StructureList()), StructureRelation.PC);
        }};
        lst1.mergeIn(lst2);
        System.out.println(lst1.toString());
        assertThat(lst1.toString()).isEqualTo(
            "SELECT { ..first, ..last, .addr: SELECT {  } NESTEDBY [@] } NESTEDBY [@]"
        );
    }
}
package jp4js.nf2.op.structure;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StructureListTest {
    @Test
    public void basic01_() {
        StructureList lst = new StructureList(StructureType.REPEATABLE) {{
            put("first", null, StructureRelation.AD);
            put("last", null, StructureRelation.AD);
            put("addr", new StructureList(StructureType.REPEATABLE), StructureRelation.PC);
        }};
        // System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo(
            "[.addr[], ..first, ..last]"
        );
    }

    @Test
    public void basic02_() {
        StructureList lst1 = new StructureList(StructureType.REPEATABLE) {{
            put("first", null, StructureRelation.AD);
            put("last", null, StructureRelation.AD);
        }};
        StructureList lst2 = new StructureList(StructureType.REPEATABLE) {{
            put("addr", new StructureList(StructureType.REPEATABLE), StructureRelation.PC);
        }};
        lst1.mergeIn(lst2);
        // System.out.println(lst1.toString());
        assertThat(lst1.toString()).isEqualTo(
            "[.addr[], ..first, ..last]"
        );
    }
}
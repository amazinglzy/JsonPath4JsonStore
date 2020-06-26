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
        System.out.println(lst.toString());
        assertThat(lst.toString()).isEqualTo(
            "[..last, .addr[], ..first]"
        );
    }
}
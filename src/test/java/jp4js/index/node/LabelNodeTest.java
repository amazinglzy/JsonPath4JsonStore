package jp4js.index.node;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LabelNodeTest {

    @Test
    public void TestSanity() {
        LabelNode a = NodeFactory.create(0, 1, 20, 2, null, null);
        LabelNode b = NodeFactory.create("label", 2, 19, 1, "df", null);
        assertThat(a.getFirstVisit()).isEqualTo(1);
        assertThat(a.getLastVisit()).isEqualTo(20);
        assertThat(b.getLevel()).isEqualTo(1);
        assertThat(b.getValue()).isEqualTo("df");
    }

    @Test
    public void TestComparable() {
        LabelNode a = NodeFactory.create(0, 1, 20, 2, null, null);
        LabelNode b = NodeFactory.create("label", 2, 19, 1, "df", null);
        assertThat(a.compareTo(b)).isLessThan(0);
    }
}

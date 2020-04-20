package jp4js.index.node;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestNode {

    @Test
    public void TestSanity() {
        Node a = NodeFactory.create(0, 1, 20, 2, null, null);
        Node b = NodeFactory.create("label", 2, 19, 1, "df", null);
        assertThat(a.getFirstVisit()).isEqualTo(1);
        assertThat(a.getLastVisit()).isEqualTo(20);
        assertThat(b.getLevel()).isEqualTo(1);
        assertThat(b.getValue()).isEqualTo("df");
    }

    @Test
    public void TestComparable() {
        Node a = NodeFactory.create(0, 1, 20, 2, null, null);
        Node b = NodeFactory.create("label", 2, 19, 1, "df", null);
        assertThat(a.compareTo(b)).isLessThan(0);
    }
}

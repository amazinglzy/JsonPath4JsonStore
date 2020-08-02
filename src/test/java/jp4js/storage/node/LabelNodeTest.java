package jp4js.storage.node;

import jp4js.nf2.Scalar;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LabelNodeTest {

    @Test
    public void TestSanity() {
        LabelNode a = NodeFactory.create(null, 0, 1, 20, 2, null);
        LabelNode b = NodeFactory.create(null, "label", 2, 19, 1, Scalar.createDString("df"));
        assertThat(a.first_visit).isEqualTo(1);
        assertThat(a.last_visit).isEqualTo(20);
        assertThat(b.level).isEqualTo(1);
        assertThat(b.value).isEqualToComparingFieldByField(Scalar.createDString("df"));
    }

    @Test
    public void TestComparable() {
        LabelNode a = NodeFactory.create(null, 0, 1, 20, 2, null);
        LabelNode b = NodeFactory.create(null, "label", 2, 19, 1, Scalar.createDString("df"));
        assertThat(a.compareTo(b)).isLessThan(0);
    }
}

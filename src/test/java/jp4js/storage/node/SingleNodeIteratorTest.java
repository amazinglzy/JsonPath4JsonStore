package jp4js.storage.node;

import jp4js.storage.node.LabelNode;
import jp4js.storage.node.NodeFactory;
import jp4js.storage.node.SingleNodeIterator;
import jp4js.utils.iter.Iter;

import java.util.LinkedList;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SingleNodeIteratorTest {
    @Test
    public void testSingleNodeIteratorSanity() {
        LinkedList<LabelNode> l = new LinkedList<LabelNode>() {{
            add(NodeFactory.create(null, 0, 0, 10, 1, null, null));
            add(NodeFactory.create(null, 0, 1, 5, 2, null, null));
        }};

        Iter<LabelNode> iter = new SingleNodeIterator(l);
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(null, 0, 0, 10, 1, null, null));
        iter.next();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(null, 0, 1, 5, 2, null, null));
        iter.next();
        assertThat(iter.hasNext()).isEqualTo(false);
    }

    @Test
    public void testSingleNodeIteratorClone() {
        LinkedList<LabelNode> l = new LinkedList<LabelNode>() {{
            add(NodeFactory.create(null, 0, 0, 10, 1, null, null));
            add(NodeFactory.create(null, 0, 1, 5, 2, null, null));
        }};

        Iter<LabelNode> iter = new SingleNodeIterator(l);

        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(null, 0, 0, 10, 1, null, null));

        Iter<LabelNode> iterCopy = iter.cloneCurrentIterator();

        iter.next();
        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(null, 0, 1, 5, 2, null, null));
        assertThat(iterCopy.read()).isEqualToIgnoringNullFields(NodeFactory.create(null, 0, 0, 10, 1, null, null));
    }
}

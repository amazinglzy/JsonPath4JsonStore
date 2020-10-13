package jp4js.storage.dewey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import jp4js.algebra.Scalar;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.utils.iter.Iter;

public class NodeOrderedListTest {
    private ArrayList<IndexNode> data = new ArrayList<>() {{
        add(new IndexNode(new LinkedList<>(Arrays.asList(0, 0)), Scalar.createDInt(1)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(0, 1)), Scalar.createDInt(2)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 0)), Scalar.createDInt(3)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 1)), Scalar.createDInt(4)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 2)), Scalar.createDInt(5)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 3)), Scalar.createDInt(6)));
    }};

    @Test
    public void basic01_addAll() {
        NodeOrderedList o = new NodeOrderedList(1);
        NodeOrderedList o1 = new NodeOrderedList(2, createIter(1, 0, 2));
        NodeOrderedList o2 = new NodeOrderedList(2, createIter(1, 2, 3));
        o.addAll(o1);
        o.addAll(o2);

        assertThat(o.nodes.size()).isEqualTo(3);
    }

    @Test
    public void basic02_crossProduct() {
        NodeOrderedList o = new NodeOrderedList(1);
        NodeOrderedList o1 = new NodeOrderedList(2, createIter(1, 0, 2));
        NodeOrderedList o2 = new NodeOrderedList(2, createIter(1, 2, 4));
        o.crossProduct(o1);

        assertThat(o.nodes.size()).isEqualTo(2);

        o.crossProduct(o2);

        assertThat(o.nodes.size()).isEqualTo(4);
    }

    @Test
    public void basic03_shrink() {
        NodeOrderedList o = new NodeOrderedList(1);
        NodeOrderedList o1 = new NodeOrderedList(2, createIter(1, 0, 4));
        NodeOrderedList o2 = new NodeOrderedList(2, createIter(0, 0, 1));
        o.addAll(o1);
        o.addAll(o2);

        assertThat(o.nodes.size()).isEqualTo(5);

        o.shrink();

        assertThat(o.nodes.size()).isEqualTo(2);

    }

    private Iter<IndexNode> createIter(int firstIndex, int from, int to) {
        StructureSteps steps = new StructureSteps();
        steps.addStep(firstIndex, firstIndex + 1);
        steps.addStep(from, to);
        StepsIterator ret = new StepsIterator(steps, this.data);
        return ret;
    }
}

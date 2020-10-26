package jp4js.storage.dewey;

import jp4js.algebra.Domain;
import jp4js.algebra.Scalar;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.algebra.tpl.AtomicValue;
import jp4js.utils.iter.CompareIter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;


public class StepsIteratorTest {

    private ArrayList<IndexNode> data = new ArrayList<>() {{
        add(new IndexNode(new LinkedList<>(Arrays.asList(0, 0)), Scalar.createDInt(1)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(0, 1)), Scalar.createDInt(2)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 0)), Scalar.createDInt(3)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 1)), Scalar.createDInt(4)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 2)), Scalar.createDInt(5)));
        add(new IndexNode(new LinkedList<>(Arrays.asList(1, 3)), Scalar.createDInt(6)));
    }};
    
    @Test
    public void basic01_() {
        StructureSteps steps = new StructureSteps();
        steps.addStep(StructureRelation.PC, "*");
        steps.addStep(1, 2);

        StepsIterator iter = new StepsIterator(steps, this.data);

        CompareIter.assertEqual(
            iter,
            new LinkedList<Domain.Instance>(
                Arrays.asList(
                    Scalar.createDInt(2),
                    Scalar.createDInt(4)
                )
            ),
            (IndexNode node, Domain.Instance ins) -> {
                if (node.data instanceof AtomicValue) {
                    AtomicValue av = (AtomicValue)node.data;
                    return av.data().equals(ins);
                }
                return false;
            }
        );
    }

    @Test
    public void basic02_() {
        StructureSteps steps = new StructureSteps();
        steps.addStep(1, 2);
        steps.addStep(StructureRelation.PC, "*");

        StepsIterator iter = new StepsIterator(steps, this.data);

        CompareIter.assertEqual(
            iter,
            new LinkedList<Domain.Instance>(
                Arrays.asList(
                    Scalar.createDInt(3),
                    Scalar.createDInt(4),
                    Scalar.createDInt(5),
                    Scalar.createDInt(6)
                )
            ),
            (IndexNode node, Domain.Instance ins) -> {
                if (node.data instanceof AtomicValue) {
                    AtomicValue av = (AtomicValue)node.data;
                    return av.data().equals(ins);
                }
                return false;
            }
        );

    }

    @Test
    public void basic03_() {
        StructureSteps steps = new StructureSteps();

        steps.addStep(1, 2);
        steps.addStep(1, 2);

        StepsIterator iter = new StepsIterator(steps, this.data);

        CompareIter.assertEqual(
            iter,
            new LinkedList<Domain.Instance>(
                Arrays.asList(
                    Scalar.createDInt(4)
                )
            ),
            (IndexNode node, Domain.Instance ins) -> {
                if (node.data instanceof AtomicValue) {
                    AtomicValue av = (AtomicValue)node.data;
                    return av.data().equals(ins);
                }
                return false;
            }
        );
    }

    @Test
    public void basic04_emptySteps() {
        StructureSteps steps = new StructureSteps();

        StepsIterator iter = new StepsIterator(steps, this.data);

        CompareIter.assertEqual(
            iter,
            new LinkedList<Domain.Instance>(
                Arrays.asList(
                    Scalar.createDInt(1),
                    Scalar.createDInt(2),
                    Scalar.createDInt(3),
                    Scalar.createDInt(4),
                    Scalar.createDInt(5),
                    Scalar.createDInt(6)
                )
            ),
            (IndexNode node, Domain.Instance ins) -> {
                if (node.data instanceof AtomicValue) {
                    AtomicValue av = (AtomicValue)node.data;
                    return av.data().equals(ins);
                }
                return false;
            }
        );
    }
}

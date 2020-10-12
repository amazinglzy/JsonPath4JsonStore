package jp4js.storage.dewey;


import jp4js.algebra.DType;
import jp4js.algebra.Scalar;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.utils.Configuration;
import jp4js.utils.algebra.Trans;
import jp4js.utils.iter.CompareIter;
import jp4js.utils.iter.Iter;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;



public class DeweyIndexTest {

    private final DeweyIndex basicIndex;
    {
        String s = "{'a': [1, 2, 3], 'b': {'a': 8}}";
        Configuration configuration = Configuration.defaultConfiguration();
        Object json = configuration.jsonProvider().parse(s);

        DType.Instance ins = Trans.fromJSON(json, configuration);
        basicIndex = DeweyIndex.build(ins);
    }
    
    @Test
    public void basic01_() {

        StructureSteps steps = new StructureSteps();
        steps.addStep(StructureRelation.PC, "a");
        steps.addStep(0, 1);

        Iter<IndexNode> iter = basicIndex.query(steps);

        CompareIter.assertEqual(
            iter,
            new LinkedList<DType.Instance>(
                Arrays.asList(
                    Scalar.createDInt(1)
                )
            ),
            (IndexNode node, DType.Instance val) -> {
                return node.value.equals(val);
            }
        );
    }

    @Test
    public void basic02_() {
        StructureSteps steps = new StructureSteps();
        steps.addStep(StructureRelation.AD, "a");

        Iter<IndexNode> iter = basicIndex.query(steps);
        CompareIter.assertEqual(
            iter,
            new LinkedList<DType.Instance>(
                Arrays.asList(
                    Scalar.createDListValues(
                        Arrays.asList(
                            Scalar.createDInt(1),
                            Scalar.createDInt(2),
                            Scalar.createDInt(3)
                        )
                    ),
                    Scalar.createDInt(8)
                )
            ),
            (IndexNode node, DType.Instance val) -> {
                return node.value.equals(val);
            }
        );

    }
}

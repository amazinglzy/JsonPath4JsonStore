package jp4js.storage.dewey;

import org.junit.Test;

import jp4js.algebra.DType;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.storage.dewey.DeweyIndex.TreeNode;
import jp4js.utils.Configuration;
import jp4js.utils.algebra.Trans;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.TreeSet;


public class DeweyIndexTest {
    
    @Test
    public void basic01_() {
        String s = "{'a': [1, 2, 3], 'b': {'a': 8}}";
        Configuration configuration = Configuration.defaultConfiguration();
        Object json = configuration.jsonProvider().parse(s);

        DType.Instance ins = Trans.fromJSON(json, configuration);

        DeweyIndex index = DeweyIndex.build(ins);

        StructureSteps steps = new StructureSteps();
        steps.addStep(StructureRelation.PC, "a");
        steps.addStep(0, 1);

        TreeSet<TreeNode> set = index.query(steps);
        assertThat(set.size()).isEqualTo(1);
    }
}

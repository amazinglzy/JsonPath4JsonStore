package jp4js.storage.dewey;


import jp4js.algebra.DType;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.utils.Configuration;
import jp4js.utils.algebra.Trans;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;



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
    }
}

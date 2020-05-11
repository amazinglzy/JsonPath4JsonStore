package jp4js.query.navigation;

import jp4js.utils.Configuration;
import jp4js.index.IndexContext;
import jp4js.index.Indexer;
import jp4js.utils.iter.Iter;
import jp4js.query.IndexPropertyScan;
import jp4js.query.PlanOperator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ADJoinTest {
    @Test
    public void testADJoinSanity01() {
        String str = "{\n" +
                "    \"level3\": {\n" +
                "        \"level2\": {\n" +
                "            \"level1\": 3\n" +
                "        }\n" +
                "    },\n" +
                "    \"level2\": {\n" +
                "        \"level1\": 2\n" +
                "    },\n" +
                "    \"level1\": 1\n" +
                "}";
        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);

        PlanOperator<Item> level1 = NavigationPlanOp.createLabelNode2Item(new IndexPropertyScan(indexContext, "level1"));
        PlanOperator<Item> level3 = NavigationPlanOp.createLabelNode2Item(new IndexPropertyScan(indexContext, "level3"));

        Iter<Item> iter;

        iter = NavigationPlanOp.createADJoin(level3, level1).iterator();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.read().getData().getValue()).isEqualTo(3);
    }
}

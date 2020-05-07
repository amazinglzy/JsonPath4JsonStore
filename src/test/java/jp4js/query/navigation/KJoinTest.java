package jp4js.query.navigation;

import jp4js.utils.Configuration;
import jp4js.index.IndexContext;
import jp4js.index.Indexer;
import jp4js.utils.iter.Iter;
import jp4js.query.IndexPropertyScan;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KJoinTest {
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

        NormalWrapper level1 = new NormalWrapper(new IndexPropertyScan(indexContext, "level1"));
        NormalWrapper level2 = new NormalWrapper(new IndexPropertyScan(indexContext, "level2"));
        NormalWrapper level3 = new NormalWrapper(new IndexPropertyScan(indexContext, "level3"));

        Iter<Item> iter;

        iter = new KJoin(level3, level1, 2).iterator();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.read().getData().getValue()).isEqualTo(3);

        iter = new KJoin(level3, level1, 1).iterator();
        assertThat(iter.hasNext()).isFalse();

        iter = new KJoin(level2, level1, 1).iterator();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.read().getData().getValue()).isEqualTo(2); iter.next();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.read().getData().getValue()).isEqualTo(3); iter.next();

    }
}

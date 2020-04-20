package jp4js.query;

import jp4js.utils.Configuration;
import jp4js.index.IndexContext;
import jp4js.index.Indexer;
import jp4js.index.node.NodeIterator;
// import jp4js..path.ArrayIndexOperation;
// import jp4js.internal.path.ArraySliceOperation;
import jp4js.query.IndexArrayScan;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexArrayScanTest {
    @Test
    public void testSanityIndex01() {
        String str = "{\n" +
                "    \"a\": [1, 2, 3]\n" +
                "}";
        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);

        // ArrayIndexOperation operation = ArrayIndexOperation.parse("1,2");

        // IndexArrayScan scan = new IndexArrayScan(indexContext, operation, null);
        // NodeIterator iter = scan.iterator();

        // assertThat(iter.hasNext()).isTrue();
        // assertThat(iter.read().getValue()).isEqualTo(2);
    }

    @Test
    public void testSanitySlice01() {
        String str = "{\n" +
                "    \"a\": [1, 2, 3]\n" +
                "}";
        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);

        // ArraySliceOperation operation = ArraySliceOperation.parse(":1");

        // IndexArrayScan scan = new IndexArrayScan(indexContext, null, operation);
        // NodeIterator iter = scan.iterator();

        // assertThat(iter.hasNext()).isTrue();
        // assertThat(iter.read().getValue()).isEqualTo(1);

    }
}

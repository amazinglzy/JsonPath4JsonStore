package jp4js.index;

import jp4js.utils.Configuration;
import jp4js.index.node.NodeFactory;
import jp4js.index.node.NodeIterator;
import static jp4js.index.node.ArrayNode.*;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.LinkedList;

public class IndexerTest {
    @Test
    public void testIndexerSanity() {
        final String str =
                "{\n" +
                        "    \"a\": {\n" +
                        "        \"b\": {\n" +
                        "            \"c\": \"value\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"d\": [\n" +
                        "        1,\n" +
                        "        2,\n" +
                        "        3\n" +
                        "    ]\n" +
                        "}";
        /*
        $   0   15  0
        a   1   6   1
        b   2   5   2
        c   3   4   3
        d   7   14   1
        0   8   9   2
        1   10   11  2
        2   12  13  2
         */
        final Configuration configuration = Configuration.defaultConfiguration();
        final IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);
        assertThat(indexContext.openArray(1L).read()).isEqualToIgnoringNullFields(
                NodeFactory.create(1, 10, 11, 2, 2, null)
        );
        assertThat(indexContext.openObject("$").read()).isEqualToIgnoringNullFields(
                NodeFactory.create("$", 0, 15, 0, null, null)
        );
        assertThat(indexContext.openArray(2L).read()).isEqualToIgnoringNullFields(
                NodeFactory.create(2, 12, 13, 2, 3, null)
        );
    }

    @Test
    public void testIndexerOrderOfIndexContext() {
        final String str = "{\n" +
                "    \"a\": {\n" +
                "        \"a\": {\n" +
                "            \"b\": \"value\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"b\": 2\n" +
                "}";
        /*
        $   0   9   0
        a   1   6   1
        a   2   5   2
        b   3   4   3
        b   7   8   1
         */
        final Configuration configuration = Configuration.defaultConfiguration();
        final IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);
        final NodeIterator iter = indexContext.openObject("a");
        assertThat(iter.read()).isEqualToIgnoringNullFields(
                NodeFactory.create("a", 1, 6, 1, null, null)
        );
        iter.next();
        assertThat(iter.read()).isEqualToIgnoringNullFields(
                NodeFactory.create("a", 2, 5, 2, null, null)
        );
    }

    @Test
    public void testIndexContextOpenArrayOperation() {
        String str =
                "{\n" +
                        "    \"a\": {\n" +
                        "        \"b\": {\n" +
                        "            \"c\": \"value\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"d\": [\n" +
                        "        1,\n" +
                        "        2,\n" +
                        "        3\n" +
                        "    ]\n" +
                        "}";
        /*
        $   0   15  0
        a   1   6   1
        b   2   5   2
        c   3   4   3
        d   7   14   1
        0   8   9   2
        1   10   11  2
        2   12  13  2
         */
        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);
        ArraySelections selections = new ArrayOperation(new LinkedList<>(){{
                add(new ArrayIndex(2));
                add(new ArraySlice(0, 2));
        }});
        NodeIterator iter = indexContext.openArray(selections);

        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read()).isEqualToIgnoringNullFields(
                NodeFactory.create(0, 8, 9, 2, null, null)
        );
        iter.next();

        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read()).isEqualToIgnoringNullFields(
                NodeFactory.create(1, 10, 11, 2, null, null)
        );
        iter.next();

        assertThat(iter.hasNext());
        assertThat(iter.read()).isEqualToIgnoringNullFields(
                NodeFactory.create(2, 12, 13, 2, null, null)
        );
        iter.next();
    }
}

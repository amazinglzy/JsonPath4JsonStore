package jp4js.storage.region;

import org.junit.Test;

import jp4js.storage.region.node.IndexNode;
import jp4js.storage.region.node.RepeatableNode;
import jp4js.storage.region.node.SingularNode;
import jp4js.utils.Configuration;
import jp4js.utils.iter.Iter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

public class IndexerTest {
    @Test
    public void basic01_() {
        String s = "{'a': [1, 2, 3], 'b': {'a': 8}}";

        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(s), configuration);
        List<IndexNode> lst = new LinkedList<>();

        Iter<IndexNode> iter = indexContext.new AllIterator();
        while (iter.valid()) {
            lst.add(iter.read());
            iter.next();
        }
        assertThat(lst.size()).isEqualTo(7);

    }

    @Test
    public void basic02_() {
        String s = "{'a': [1, 2, 3], 'b': {'a': 8}}";

        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(s), configuration);
        List<IndexNode> lst = new LinkedList<>();

        Iter<RepeatableNode> rIter = indexContext.new RepeatableIterator(2);
        lst.clear();
        while (rIter.valid()) {
            lst.add(rIter.read());
            rIter.next();
        }
        assertThat(lst.size()).isEqualTo(3);
    }

    @Test
    public void basic03_() {
        String s = "{'a': [1, 2, 3], 'b': {'a': 8}}";

        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(s), configuration);
        List<IndexNode> lst = new LinkedList<>();

        Iter<SingularNode> sIter = indexContext.new SingularIterator("a");
        while (sIter.valid()) {
            lst.add(sIter.read());
            sIter.next();
        }
        assertThat(lst.size()).isEqualTo(2);
    }
}
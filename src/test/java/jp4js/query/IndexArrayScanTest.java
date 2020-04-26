package jp4js.query;

import jp4js.utils.Configuration;
import jp4js.index.IndexContext;
import jp4js.index.Indexer;
import jp4js.index.node.LabelNode;
import jp4js.utils.Iter;
import jp4js.index.node.LabelArray.*;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

public class IndexArrayScanTest {
    final static IndexContext basic01;
    static {
        String str = "{\n" +
                "    \"a\": [1, 2, 3]\n" +
                "}";
        Configuration configuration = Configuration.defaultConfiguration();
        basic01 = Indexer.index(configuration.jsonProvider().parse(str), configuration);
    }

    @Test
    public void testSanityIndex01() {
        String str = "{\n" +
                "    \"a\": [1, 2, 3]\n" +
                "}";
        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);

        ArraySelections selections = new ArrayIndex(1);

        IndexArrayScan scan = new IndexArrayScan(indexContext, selections);
        Iter<LabelNode> iter = scan.iterator();

        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read().getValue()).isEqualTo(2);
    }

    @Test
    public void testSanitySlice01() {
        String str = "{\n" +
                "    \"a\": [1, 2, 3]\n" +
                "}";
        Configuration configuration = Configuration.defaultConfiguration();
        IndexContext indexContext = Indexer.index(configuration.jsonProvider().parse(str), configuration);

        ArraySelections selections = new ArraySlice(0, 3);

        IndexArrayScan scan = new IndexArrayScan(indexContext, selections);
        Iter<LabelNode> iter = scan.iterator();

        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read().getValue()).isEqualTo(1);
    }
    
    @Test
    public void testArrayIndexSelection() {
        ArrayIndex index = new ArrayIndex(1);
        List<Integer> sels = index.select();
        
        assertThat(sels.size()).isEqualTo(1);
        assertThat(sels.get(0)).isEqualTo(1);
    }

    @Test
    public void testArraySliceSelection() {
        ArraySlice slice = new ArraySlice(1, 3);
        List<Integer> sels = slice.select();

        assertThat(sels.size()).isEqualTo(2);
        assertThat(sels.get(0)).isEqualTo(1);
        assertThat(sels.get(1)).isEqualTo(2);
    }
    
    @Test
    public void testArrayOperationSelection() {
        ArrayOperation operation = new ArrayOperation(
            new LinkedList<ArraySelections>(){{
                add(new ArrayIndex(1));
                add(new ArrayIndex(10));
                add(new ArraySlice(5, 9));
                add(new ArrayIndex(2));
            }}
        );
        List<Integer> sels = operation.select();
        assertThat(sels.size()).isEqualTo(7);
        assertThat(sels.get(0)).isEqualTo(1);
        assertThat(sels.get(1)).isEqualTo(10);
        assertThat(sels.get(2)).isEqualTo(5);
        assertThat(sels.get(3)).isEqualTo(6);
        assertThat(sels.get(4)).isEqualTo(7);
        assertThat(sels.get(5)).isEqualTo(8);
        assertThat(sels.get(6)).isEqualTo(2);
    }
}

package jp4js.integrate.sanity;

import jp4js.JsonData;
import jp4js.utils.Configuration;
import jp4js.query.RecordSet.Record;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Iterator;

import org.junit.Test;

public class OnlyPathSanityTest {
    private static Configuration configuration = Configuration.defaultConfiguration();
    private static Object example01 = JsonData.createJson(JsonData.EXAMPLE02, configuration);
    private static Object example02 = JsonData.createJson(JsonData.EXAPMLE01, configuration);
    private static Object example03 = JsonData.createJson(JsonData.EXAMPLE03, configuration);
    private static Object example04 = JsonData.createJson(JsonData.EXAMPLE04, configuration);


    public void testSanity(JsonPathEval eval) {
        Iterator<Record> iter;
        iter = eval.eval("$.field3[*]", example01, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(1);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(2);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(3);
        assertThat(iter.hasNext()).isFalse();

        iter = eval.eval("$.store.book[*].author", example02, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Nigel Rees");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Evelyn Waugh");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Herman Melville");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("J. R. R. Tolkien");
        assertThat(iter.hasNext()).isFalse();

        iter = eval.eval("$..author", example02, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Nigel Rees");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Evelyn Waugh");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Herman Melville");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("J. R. R. Tolkien");
        assertThat(iter.hasNext()).isFalse();

        iter = eval.eval("$.store..price", example02, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(19.95);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(8.95);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(12.99);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(8.99);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(22.99);
        assertThat(iter.hasNext()).isFalse();

        iter = eval.eval("$.phoneNumbers[0:1, 1].type", example03, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("iPhone");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("home");
        assertThat(iter.hasNext()).isFalse();

        iter = eval.eval("$..a..b", example04, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(1);
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testJsonPathScanSanity() {
        JsonPathEval eval = new JsonPathScan();
        testSanity(eval);
    }

    @Test
    public void testJsonPathMergeJoinDSanity() {
        JsonPathEval eval = new JsonPathMergeJoinD();
        testSanity(eval);
    }

    @Test
    public void testJsonPathMergeJoinSSanity() {
        JsonPathEval eval = new JsonPathMergeJoinS();
        testSanity(eval);
    }
}
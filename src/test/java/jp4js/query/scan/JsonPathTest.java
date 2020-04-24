package jp4js.query.scan;

import jp4js.utils.Configuration;
import jp4js.query.JsonData;
import jp4js.query.RecordSet.Record;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;

public class JsonPathTest {
    private static Configuration configuration = Configuration.defaultConfiguration();
    private static Object example01 = JsonData.createJson(JsonData.EXAMPLE02, configuration);
    private static Object example02 = JsonData.createJson(JsonData.EXAPMLE01, configuration);
    private static Object example03 = JsonData.createJson(JsonData.EXAMPLE03, configuration);

    @Test
    public void testJsonPathExample0101() {
        Iterator<Record> iter = JsonPath.evaluate("$.field3[*]", example01, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(1);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(2);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(3);
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testJsonPathExample0201() {
        Iterator<Record> iter = JsonPath.evaluate("$.store.book[*].author", example02, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Nigel Rees");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Evelyn Waugh");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Herman Melville");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("J. R. R. Tolkien");
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testJsonPathExample0202() {
        Iterator<Record> iter = JsonPath.evaluate("$..author", example02, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Nigel Rees");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Evelyn Waugh");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("Herman Melville");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("J. R. R. Tolkien");
        assertThat(iter.hasNext()).isFalse();

    }

    @Test
    public void testJsonPathExample0203() {
        Iterator<Record> iter = JsonPath.evaluate("$.store..price", example02, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(8.95);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(12.99);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(8.99);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(22.99);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(19.95);
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testJsonPathExample03() {
        Iterator<Record> iter = JsonPath.evaluate("$.phoneNumbers[0:1].type", example03, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("iPhone");
        assertThat(iter.hasNext()).isFalse();
    };
}
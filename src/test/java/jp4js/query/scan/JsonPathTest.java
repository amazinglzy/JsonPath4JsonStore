package jp4js.query.scan;

import jp4js.utils.Configuration;
import jp4js.query.RecordSet.Record;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;

public class JsonPathTest {
    private static Object basic01;
    private static Configuration configuration = Configuration.defaultConfiguration();

    static {
        String str = "{\n" +
            "\"field1\": {\n" +
                "\"field1\": [3]" +
            "}," +
            "\"field2\": { " +
                "\"field1\": \"field2-field1\"" +
            "}, "+
            "\"field3\": [1, 2, 3]"+
        "}";
        basic01 = configuration.jsonProvider().parse(str);
    }

    @Test
    public void testJsonPathBasic01() {
        Iterator<Record> iter = JsonPath.evaluate("$.field3[*]", basic01, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(1);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(2);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(3);
    }
}
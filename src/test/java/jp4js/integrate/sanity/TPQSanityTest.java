package jp4js.integrate.sanity;

import jp4js.JsonData;
import jp4js.utils.Configuration;
import jp4js.query.RecordSet.Record;
// import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Iterator;

public class TPQSanityTest {
    private static Configuration configuration = Configuration.defaultConfiguration();
    private static Object example01 = JsonData.createJson(JsonData.EXAPMLE01, configuration);
    // private static Object example03 = JsonData.createJson(JsonData.EXAMPLE03, configuration);

    public void testSanity(JsonPathEval eval) {
        Iterator<Record> iter;
        iter = eval.eval("$.store.book.*[?(@.isbn)].price", example01, configuration);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(8.99);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(22.99);
        assertThat(iter.hasNext()).isFalse();
    }
}
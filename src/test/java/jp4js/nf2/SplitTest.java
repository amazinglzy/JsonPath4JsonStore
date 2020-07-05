package jp4js.nf2;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import jp4js.JsonData;
import jp4js.nf2.op.Split;
import jp4js.query.JsonPathSample;
import jp4js.utils.Configuration;
import jp4js.utils.nf2.Trans;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitTest {
    @Test
    public void basic01_() {
        Configuration configuration = Configuration.defaultConfiguration();
        Object example01 = JsonData.createJson(JsonData.EXAPMLE01, configuration);
        DType.Instance instance = Trans.fromJSON(example01, configuration);

        Split split = new Split(instance, JsonPathSample.lst1);
        try {
            Match match = split.open();
            assertThat(match.isValid()).isTrue();
            // System.out.println(match.header().toString());
            // System.out.println(match.body().toString());
            assertThat(match.header().toString()).isEqualTo(
                "(store(book[author()]))"
            );
            assertThat(match.body().toString()).isEqualTo(
                "(([(\"Nigel Rees\"), (\"Evelyn Waugh\"), (\"Herman Melville\"), (\"J. R. R. Tolkien\")]))"
            );
        } catch (Split.MatchException e) {
            assertFalse("open failed", true);
        }
    }
}
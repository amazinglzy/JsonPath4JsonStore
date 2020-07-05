package jp4js.nf2;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import jp4js.data.Goessener;
import jp4js.nf2.op.Split;
import jp4js.query.JsonPathSample;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitTest {
    @Test
    public void basic01_() {
        DType.Instance instance = new Goessener().instance();

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
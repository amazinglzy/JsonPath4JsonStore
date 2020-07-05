package jp4js.utils.query;

import org.junit.Test;

import jp4js.query.JsonPathSample;

import static org.assertj.core.api.Assertions.assertThat;

public class PathCompilerTest {
    @Test
    public void basic01_() {
        assertThat(JsonPathSample.lst1.toString()).isEqualTo(
            "(.store(.book[.author()]))"
        );
        assertThat(JsonPathSample.lst2.toString()).isEqualTo(
            "(..author())"
        );
        assertThat(JsonPathSample.lst3.toString()).isEqualTo(
            "(.store(.*()))"
        );
        assertThat(JsonPathSample.lst4.toString()).isEqualTo(
            "(.store(..price()))"
        );
        assertThat(JsonPathSample.lst5.toString()).isEqualTo(
            "(..book[])"
        );

    }
}
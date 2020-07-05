package jp4js.utils.query;

import org.junit.Test;

import jp4js.data.Goessener;

import static org.assertj.core.api.Assertions.assertThat;

public class PathCompilerTest {
    @Test
    public void basic01_() {
        Goessener data = new Goessener();
        assertThat(data.query(0).toString()).isEqualTo(
            "(.store(.book[.author()]))"
        );
        assertThat(data.query(1).toString()).isEqualTo(
            "(..author())"
        );
        assertThat(data.query(2).toString()).isEqualTo(
            "(.store(.*()))"
        );
        assertThat(data.query(3).toString()).isEqualTo(
            "(.store(..price()))"
        );
        assertThat(data.query(4).toString()).isEqualTo(
            "(..book[])"
        );

    }
}
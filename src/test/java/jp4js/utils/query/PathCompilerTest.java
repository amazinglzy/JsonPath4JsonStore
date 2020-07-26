package jp4js.utils.query;

import org.junit.Test;

import jp4js.data.Goessener;
import jp4js.data.JsonArrayMulti;

import static org.assertj.core.api.Assertions.assertThat;

public class PathCompilerTest {
    @Test
    public void basic01_() {
        Goessener data = new Goessener();
        assertThat(data.query(0).toString()).isEqualTo(
            "asynomous(.store:asynomous(.book:asynomous[.author:asynomous()]))"
        );
        assertThat(data.query(1).toString()).isEqualTo(
            "asynomous(..author:asynomous())"
        );
        assertThat(data.query(2).toString()).isEqualTo(
            "asynomous(.store:asynomous(.*:asynomous()))"
        );
        assertThat(data.query(3).toString()).isEqualTo(
            "asynomous(.store:asynomous(..price:asynomous()))"
        );
        assertThat(data.query(4).toString()).isEqualTo(
            "asynomous(..book:asynomous[])"
        );
    }

    @Test
    public void basic02_() {
        JsonArrayMulti data = new JsonArrayMulti();
        assertThat(data.query(0).toString()).isEqualTo("asynomous(.embedded:asynomous(..count:asynomous(), ..difference:asynomous()))");
    }
}
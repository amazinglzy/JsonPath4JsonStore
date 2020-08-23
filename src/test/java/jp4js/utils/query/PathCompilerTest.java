package jp4js.utils.query;

import org.junit.Test;

import jp4js.data.Goessener;
import jp4js.data.JsonArrayMulti;

import static org.assertj.core.api.Assertions.assertThat;

public class PathCompilerTest {
    @Test
    public void basic01_() {
        Goessener data = new Goessener();
        // System.out.println(data.query(0).toString());
        // System.out.println(data.query(0).toString());
        // System.out.println(data.query(0).toString());
        // System.out.println(data.query(0).toString());
        System.out.println(data.query(4).toString());
        assertThat(data.query(0).toString()).isEqualTo(
            "asynomous[.store.book.*.author:asynomous[]]"
        );
        assertThat(data.query(1).toString()).isEqualTo(
            "asynomous[..author:asynomous[]]"
        );
        assertThat(data.query(2).toString()).isEqualTo(
            "asynomous[.store.*:asynomous[]]"
        );
        assertThat(data.query(3).toString()).isEqualTo(
            "asynomous[.store..price:asynomous[]]"
        );
        assertThat(data.query(4).toString()).isEqualTo(
            "asynomous[..book.{2,3}:asynomous[]]"
        );
    }

    @Test
    public void basic02_() {
        JsonArrayMulti data = new JsonArrayMulti();
        assertThat(data.query(0).toString()).isEqualTo("asynomous[.embedded:asynomous[..count:asynomous[], ..difference:asynomous[]]]");
    }
}
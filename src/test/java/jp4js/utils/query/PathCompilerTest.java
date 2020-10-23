package jp4js.utils.query;

import org.junit.Test;

import jp4js.data.Goessener;
import jp4js.data.JsonArrayMulti;

import static org.assertj.core.api.Assertions.assertThat;

public class PathCompilerTest {
    @Test
    public void basic01_() {
        Goessener data = new Goessener();
        System.out.println(data.query(0).toString());
        System.out.println(data.query(1).toString());
        System.out.println(data.query(2).toString());
        System.out.println(data.query(3).toString());
        System.out.println(data.query(4).toString());
        assertThat(data.query(0).toString()).isEqualTo(
            "SELECT { .store.book.*.author } NESTEDBY [@]"
        );
        assertThat(data.query(1).toString()).isEqualTo(
            "SELECT { ..author } NESTEDBY [@]"
        );
        assertThat(data.query(2).toString()).isEqualTo(
            "SELECT { .store.* } NESTEDBY [@]"
        );
        assertThat(data.query(3).toString()).isEqualTo(
            "SELECT { .store..price } NESTEDBY [@]"
        );
        assertThat(data.query(4).toString()).isEqualTo(
            "SELECT { ..book.{2,3} } NESTEDBY [@]"
        );
    }

    @Test
    public void basic02_() {
        JsonArrayMulti data = new JsonArrayMulti();
        System.out.println(data.query(0).toString());
        assertThat(data.query(0).toString()).isEqualTo("SELECT { .embedded: SELECT { ..count, ..difference } } NESTEDBY [@]");
    }
}
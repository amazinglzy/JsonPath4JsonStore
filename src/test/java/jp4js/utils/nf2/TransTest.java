package jp4js.utils.nf2;

import org.junit.Test;

import jp4js.query.JsonPathSample;

import static org.assertj.core.api.Assertions.assertThat;

public class TransTest {
    @Test
    public void basic01_() {
        // System.out.println(Trans.fromSL(JsonPathSample.lst1).toString());
        // System.out.println(Trans.fromSL(JsonPathSample.lst2).toString());
        // System.out.println(Trans.fromSL(JsonPathSample.lst3).toString());
        // System.out.println(Trans.fromSL(JsonPathSample.lst4).toString());
        // System.out.println(Trans.fromSL(JsonPathSample.lst5).toString());
        assertThat(Trans.fromSL(JsonPathSample.lst1).toString()).isEqualTo("(store(book[author]))");
        assertThat(Trans.fromSL(JsonPathSample.lst2).toString()).isEqualTo("(author)");
        assertThat(Trans.fromSL(JsonPathSample.lst3).toString()).isEqualTo("(store(*))");
        assertThat(Trans.fromSL(JsonPathSample.lst4).toString()).isEqualTo("(store(price))");
        assertThat(Trans.fromSL(JsonPathSample.lst5).toString()).isEqualTo("(book[])");
    }
}
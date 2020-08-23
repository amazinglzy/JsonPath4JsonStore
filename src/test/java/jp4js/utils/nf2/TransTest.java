package jp4js.utils.nf2;

import org.junit.Test;

import jp4js.data.Goessener;
import jp4js.data.JsonPathWebsite;
import jp4js.data.NestedFieldname;
import jp4js.data.NestedSameFieldname;
import jp4js.utils.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class TransTest {
    @Test
    public void basic01_FromSL2DHeader() {
        Goessener data = new Goessener();
        // System.out.println(Trans.fromSL(data.query(0)).toString());
        // System.out.println(Trans.fromSL(data.query(1)).toString());
        // System.out.println(Trans.fromSL(data.query(2)).toString());
        // System.out.println(Trans.fromSL(data.query(3)).toString());
        // System.out.println(Trans.fromSL(data.query(4)).toString());
        assertThat(Trans.fromSL(data.query(0)).toString()).isEqualTo("[.store.book.*.author[]]");
        assertThat(Trans.fromSL(data.query(1)).toString()).isEqualTo("[..author[]]");
        assertThat(Trans.fromSL(data.query(2)).toString()).isEqualTo("[.store.*[]]");
        assertThat(Trans.fromSL(data.query(3)).toString()).isEqualTo("[.store..price[]]");
        assertThat(Trans.fromSL(data.query(4)).toString()).isEqualTo("[..book.{2,3}[]]");
    }

    @Test
    public void basic02_FromJSON2DTypeInstance() {
        Configuration configuration = Configuration.defaultConfiguration();
        Object example01 = new Goessener().json();
        Object example02 = new NestedFieldname().json();
        Object example03 = new JsonPathWebsite().json();
        Object example04 = new NestedSameFieldname().json();
        // System.out.println(Trans.fromJSON(example01, configuration).toString());
        // System.out.println(Trans.fromJSON(example02, configuration).toString());
        // System.out.println(Trans.fromJSON(example03, configuration).toString());
        // System.out.println(Trans.fromJSON(example04, configuration).toString());
        assertThat(Trans.fromJSON(example01, configuration).toString()).isEqualTo(
            "{store={bicycle={color=\"red\", price=19.95}, book=[{author=\"Nigel Rees\", category=\"reference\", price=8.95, title=\"Sayings of the Century\"}, {author=\"Evelyn Waugh\", category=\"fiction\", price=12.99, title=\"Sword of Honour\"}, {author=\"Herman Melville\", category=\"fiction\", isbn=\"0-553-21311-3\", price=8.99, title=\"Moby Dick\"}, {author=\"J. R. R. Tolkien\", category=\"fiction\", isbn=\"0-395-19395-8\", price=22.99, title=\"The Lord of the Rings\"}]}}"
        );
        assertThat(Trans.fromJSON(example02, configuration).toString()).isEqualTo(
            "{field1={field1=[3]}, field2={field1=\"field2-field1\"}, field3=[1, 2, 3]}"
        );
        assertThat(Trans.fromJSON(example03, configuration).toString()).isEqualTo(
            "{address={city=\"Nara\", postalCode=\"630-0192\", streetAddress=\"naist street\"}, age=26, firstName=\"John\", lastName=\"doe\", phoneNumbers=[{number=\"0123-4567-8888\", type=\"iPhone\"}, {number=\"0123-4567-8910\", type=\"home\"}]}"
        );
        assertThat(Trans.fromJSON(example04, configuration).toString()).isEqualTo(
            "{a={a={b=1}}}"
        );
    }
}
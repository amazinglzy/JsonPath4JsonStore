package jp4js.utils;

import org.junit.Test;

import jp4js.JsonData;
import jp4js.nf2.rel.doc.DocNode;

import static org.assertj.core.api.Assertions.assertThat;

public class TransUtilsTest {
    @Test
    public void basic01_() {
        Configuration configuration = Configuration.defaultConfiguration();
        Object json = JsonData.createJson(JsonData.EXAPMLE01, configuration);
        DocNode node = TransUtils.trans(json, configuration);
        // System.out.println(node.toString());
        assertThat(node.toString()).isEqualTo(
            "store(bicycle(color(\"red\"), price(19.95)), book(author(\"Nigel Rees\")"+
            ", category(\"reference\"), price(8.95), title(\"Sayings of the Century\")"+
            ", author(\"Evelyn Waugh\"), category(\"fiction\"), price(12.99), "+
            "title(\"Sword of Honour\"), author(\"Herman Melville\"), category(\"fiction\"), "+
            "isbn(\"0-553-21311-3\"), price(8.99), title(\"Moby Dick\"), author(\"J. R. R. Tolkien\"), "+
            "category(\"fiction\"), isbn(\"0-395-19395-8\"), price(22.99), title(\"The Lord of the Rings\")))"
        );
    }
}
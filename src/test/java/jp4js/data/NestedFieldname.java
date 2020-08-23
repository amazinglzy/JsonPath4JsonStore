package jp4js.data;

public class NestedFieldname extends BaseDataSuite {
    @Override
    public String data() {
        return
            "{\n" +
                "\"field1\": {\n" +
                    "\"field1\": [3]" +
                "}," +
                "\"field2\": { " +
                    "\"field1\": \"field2-field1\"" +
                "}, "+
                "\"field3\": [1, 2, 3]"+
            "}";
    }

    @Override
    public String[] querySet() {
        return new String[] {
            "$.field2.field1",
            "$.*.field1",
            "$..field1"
        };
    }

    @Override
    public String[] res() {
        return new String[] {
            "[([\"field2-field1\"])]",
            "[([[3]]), ([\"field2-field1\"])]",
            "[([{field1=[3]}]), ([[3]]), ([\"field2-field1\"])]"
        };
    }
}
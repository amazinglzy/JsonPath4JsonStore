package jp4js.data;

public class NestedSameFieldname extends BaseDataSuite {
    @Override
    public String data() {
        return 
            "    {" +
            "        \"a\": {" +
            "            \"a\": {" +
            "                \"b\": 1" +
            "            }" +
            "        }" +
            "    }";
    }

    @Override
    public String[] querySet() {
        return new String[] {
            "$..a.b"
        };
    }
}
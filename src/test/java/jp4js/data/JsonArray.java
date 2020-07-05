package jp4js.data;

public class JsonArray extends BaseDataSuite {
    @Override
    public String data() {
        return 
            "{" +
            "    \"arr\": [" +
            "        {" +
            "            \"obj\":{\"innerArr\": [" +
            "                {" +
            "                    \"val\": \"bla_0\"" +
            "                }," +
            "                {" +
            "                    \"val\": null" +
            "                }" +
            "            ]}" +
            "        }," +
            "        {" +
            "            \"obj\":{\"innerArr\": [" +
            "                {" +
            "                    \"val\": \"bla_4\"" +
            "                }," +
            "                {" +
            "                    \"val\": \"bla_5\"" +
            "                }" +
            "            ]}" +
            "        }" +
            "    ]" +
            "}";
    }

    @Override
    public String[] querySet() {
        return new String[] {
            "$.arr[*].obj.innerArr[*].val",
            "$..val",
            "$..innerArr[*].val"
        };
    }
}
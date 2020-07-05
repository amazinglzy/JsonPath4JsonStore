package jp4js.data;

public class JsonArrayMulti extends BaseDataSuite {
    @Override
    public String data() {
        return
            "{" +
            "    \"embedded\": {" +
            "      \"mandates\": [" +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"10\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"34\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"2\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"4\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }," +
            "        {" +
            "          \"count\": \"0\"," +
            "          \"difference\": \"+0\"" +
            "        }" +
            "      ]" +
            "    }" +
            "}";
    }

    @Override
    public String[] querySet() {
        return new String[] {
            "$.embedded[?(@..count && @..difference)]"
        };
    }

    @Override
    public String[] res() {
        return new String[] {
            "((\"0\", \"+0\"))"
        };
    }
}
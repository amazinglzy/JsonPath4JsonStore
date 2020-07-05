package jp4js.data;

public class JsonPathWebsite extends BaseDataSuite {
    @Override
    public String data() {
        return 
            "{" +
            "  \"firstName\": \"John\"," +
            "  \"lastName\" : \"doe\"," +
            "  \"age\"      : 26," +
            "  \"address\"  : {" +
            "    \"streetAddress\": \"naist street\"," +
            "    \"city\"         : \"Nara\"," +
            "    \"postalCode\"   : \"630-0192\"" +
            "  }," +
            "  \"phoneNumbers\": [" +
            "    {" +
            "      \"type\"  : \"iPhone\"," +
            "      \"number\": \"0123-4567-8888\"" +
            "    }," +
            "    {" +
            "      \"type\"  : \"home\"," +
            "      \"number\": \"0123-4567-8910\"" +
            "    }" +
            "  ] "+
            "}";
    }

    @Override
    public String[] querySet() {
        return new String[] {
            "$.phoneNumbers[*][?(@.type && @.number)]"
        };
    }

    @Override
    public String[] res() {
        return new String[] {
            "([(\"0123-4567-8888\", \"iPhone\"), (\"0123-4567-8910\", \"home\")])"
        };
    }
}
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
            "$[@.firstname && @.lastname].phoneNumbers[*][@.type && @.name]"
        };
    }

    @Override
    public String[] res() {
        return new String[] {
        };
    }
}
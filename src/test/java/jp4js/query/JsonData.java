package jp4js.query;

import jp4js.utils.Configuration;

public class JsonData {
    public static String EXAPMLE01 = 
        "{ \"store\": { " +
        "    \"book\": [ " +
        "      { \"category\": \"reference\"," +
        "        \"author\": \"Nigel Rees\"," +
        "        \"title\": \"Sayings of the Century\"," +
        "        \"price\": 8.95" +
        "      }," +
        "      { \"category\": \"fiction\"," +
        "        \"author\": \"Evelyn Waugh\"," +
        "        \"title\": \"Sword of Honour\"," +
        "        \"price\": 12.99" +
        "      }," +
        "      { \"category\": \"fiction\"," +
        "        \"author\": \"Herman Melville\"," +
        "        \"title\": \"Moby Dick\"," +
        "        \"isbn\": \"0-553-21311-3\"," +
        "        \"price\": 8.99" +
        "      }," +
        "      { \"category\": \"fiction\"," +
        "        \"author\": \"J. R. R. Tolkien\"," +
        "        \"title\": \"The Lord of the Rings\"," +
        "        \"isbn\": \"0-395-19395-8\"," +
        "        \"price\": 22.99" +
        "      }" +
        "    ]," +
        "    \"bicycle\": {" +
        "      \"color\": \"red\"," +
        "      \"price\": 19.95" +
        "    }" +
        "  } "+
        "} ";

    public static String EXAMPLE02 = "{\n" +
        "\"field1\": {\n" +
            "\"field1\": [3]" +
        "}," +
        "\"field2\": { " +
            "\"field1\": \"field2-field1\"" +
        "}, "+
        "\"field3\": [1, 2, 3]"+
    "}";

    public static String EXAMPLE03 =
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

    public static Object createJson(String jsonString, Configuration configuration) {
        return configuration.jsonProvider().parse(jsonString);
    }
}
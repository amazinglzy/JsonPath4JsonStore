package jp4js.data;

public class Goessener extends BaseDataSuite {
    @Override
    public String data() {
        return
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
    }

    @Override
    public String[] querySet() {
        return new String[] {
            "$.store.book[*].author",
            "$..author",
            "$.store.*",
            "$.store..price",
            "$..book[2:3]"
        };
    }

    @Override
    public String[] res() {
        return new String[] {
            "[[(\"Nigel Rees\"), (\"Evelyn Waugh\"), (\"Herman Melville\"), (\"J. R. R. Tolkien\")]]",
            "[[(\"Nigel Rees\"), (\"Evelyn Waugh\"), (\"Herman Melville\"), (\"J. R. R. Tolkien\")]]",
            "[[({color=\"red\", price=19.95}), ([{author=\"Nigel Rees\", category=\"reference\", price=8.95, title=\"Sayings of the Century\"}, {author=\"Evelyn Waugh\", category=\"fiction\", price=12.99, title=\"Sword of Honour\"}, {author=\"Herman Melville\", category=\"fiction\", isbn=\"0-553-21311-3\", price=8.99, title=\"Moby Dick\"}, {author=\"J. R. R. Tolkien\", category=\"fiction\", isbn=\"0-395-19395-8\", price=22.99, title=\"The Lord of the Rings\"}])]]",
            "[[(19.95), (8.95), (12.99), (8.99), (22.99)]]",
            "[[({author=\"Herman Melville\", category=\"fiction\", isbn=\"0-553-21311-3\", price=8.99, title=\"Moby Dick\"})]]"
        };
    }
}
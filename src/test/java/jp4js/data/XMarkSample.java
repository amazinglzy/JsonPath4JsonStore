package jp4js.data;

public class XMarkSample extends BaseDataSuite {
    @Override
    public String[] querySet() {
        return new String[]{
            "$.closed_auctions[*].price",
            "$.region..location",
            "$.people[*].name",
            "$.open_auctions[*].reserve"
        };
    }

    @Override
    public String[] res() {
        return new String[]{
            "([(\"45.13\")])",
            "((\"United States\"))",
            "([(\"Johnathan Senouci\")])",
            "([(\"542.00\")])"
        };
    }
    
    @Override
    public String data() {
        return 
            "    {" +
            "        \"region\": {" +
            "            \"<region_name>\": [" +
            "                {" +
            "                    \"id\": \"item0\"," +
            "                    \"location\": \"United States\"," +
            "                    \"quantity\": 1," +
            "                    \"name\": \"duteous nine eighteen\"," +
            "                    \"payment\": \"Creditcard\"," +
            "                    \"description\": {" +
            "                    \"parlist\": [" +
            "                        \"page rous lady idle authority capt professes stabs monster petition heave humbly removes rescue runs shady peace most piteous worser oak assembly holes patience but malice whoreson mirrors master tenants smocks yielded<keyword> officer embrace such fears distinction attires </keyword>\"," +
            "                        \"shepherd noble supposed dotage humble servilius bitch theirs venus dismal wounds gum merely raise red breaks earth god folds closet captain dying reek\"" +
            "                    ]" +
            "                    }," +
            "                    \"shipping\": \"Will ship internationally, See description for charges\"," +
            "                    \"incategory\": [" +
            "                        \"category67\"," +
            "                        \"category51\"," +
            "                        \"category122\"," +
            "                        \"category97\"," +
            "                        \"category1\"" +
            "                    ]," +
            "                    \"mailbox\": [" +
            "                    {" +
            "                        \"from\": \"Libero Rive mailto:Rive@hitachi.com\"," +
            "                        \"to\": \"Benedikte Glew mailto:Glew@sds.no\"," +
            "                        \"date\": \"07/05/2000\"," +
            "                        \"text\": \"virgin preventions half logotype weapons granted factious already carved fretted impress pestilent<keyword> girdles deserts flood george nobility reprieve </keyword>discomfort sinful conceiv corn preventions greatly suit observe sinews enforcement<emph> armed </emph>gold gazing set almost catesby turned servilius cook doublet preventions shrunk smooth great choice enemy disguis tender might deceit ros dreadful stabbing fold unjustly ruffian life hamlet containing leaves\"" +
            "                    }" +
            "                    ]" +
            "                }" +
            "            ]" +
            "        }," +
            "        \"categories\": [{\"name\": \"xx\", \"description\": \"xxx\"}]," +
            "        \"catgraph\": [{\"from\": \"category01\", \"to\": \"category02\"}]," +
            "        \"people\": [" +
            "            {" +
            "                \"id\": \"person0\"," +
            "                \"name\": \"Johnathan Senouci\"," +
            "                \"emailaddress\": \"mailto:Senouci@ualberta.ca\"," +
            "                \"address\": {" +
            "                    \"street\": \"55 Chubarov St\"," +
            "                    \"city\": \"Caracas\"," +
            "                    \"country\": \"Seychelles\"," +
            "                    \"zipcode\": \"5\"" +
            "                }," +
            "                \"profile\": {" +
            "                    \"business\": \"Yes\"," +
            "                    \"gender\": \"male\"," +
            "                    \"age\": \"18\"," +
            "                    \"interest\": [" +
            "                    \"category72\"," +
            "                    \"category79\"," +
            "                    \"category77\"" +
            "                    ]" +
            "                }," +
            "                \"watches\": [" +
            "                    \"open_auction1497\"" +
            "                ]" +
            "            }" +
            "        ]," +
            "        \"open_auctions\": [" +
            "            {" +
            "                \"id\": \"open_auction0\"," +
            "                \"initial\": \"89.88\"," +
            "                \"current\": \"158.88\"," +
            "                \"quantity\": \"1\"," +
            "                \"type\": \"Regular\"," +
            "                \"reserve\": \"542.00\"," +
            "                \"bidder\": [" +
            "                    {" +
            "                        \"date\": \"05/06/1999\"," +
            "                        \"time\": \"06:54:33\"," +
            "                        \"personref\": \"person2520\"," +
            "                        \"increase\": \"57.00\"" +
            "                    }," +
            "                    {" +
            "                        \"date\": \"11/23/2001\"," +
            "                        \"time\": \"00:08:50\"," +
            "                        \"personref\": \"person8\"," +
            "                        \"increase\": \"12.00\"" +
            "                    }" +
            "                ]," +
            "                \"itemref\": \"item0\"," +
            "                \"seller\": \"person2323\"," +
            "                \"interval\": {" +
            "                    \"start\": \"03/10/2001\"," +
            "                    \"end\": \"04/05/1998\"" +
            "                }" +
            "            }" +
            "        ]," +
            "        \"closed_auctions\": [" +
            "            {" +
            "                \"price\": \"45.13\"," +
            "                \"date\": \"05/19/1999\"," +
            "                \"quantity\": \"1\"," +
            "                \"type\": \"Regular\"," +
            "                \"seller\": \"person1536\"," +
            "                \"buyer\": \"person1773\"," +
            "                \"itemref\": \"item1\"," +
            "                \"annotation\": {" +
            "                    \"author\": \"person2251\"," +
            "                    \"description\": {" +
            "                    \"parlist\": [" +
            "                        \"communicate have fond vienna stain gentles marian dat here apology fret ail unexpected rowland parolles renew\"," +
            "                        \"<bold> safe ducats inclining square infringe wits cock </bold>mine returns comply chaste guide affects troilus envious bless swearing entertain parching quiver loathes dissembling faiths last makes haste offence fortune humphrey came weaker dove mood<emph> since fawn clawed speaking employ gibes smooth </emph>twenty twelvemonth lammas mild tush justice kindled eros forehead hours modern reason thyself fulfill itself pet eldest distant clergy natures educational players wretches discharg coats ditch<bold> methoughts </bold>abominable had intent prophet knots durst scene hire early thought cousins kiss sums guests fares yonder understanding majesties burgundy trusty buys slay helmets galled bias gone bowls peasant wildness detect\"" +
            "                    ]" +
            "                    }," +
            "                    \"happiness\": \"8\"" +
            "                }" +
            "            }" +
            "        ]" +
            "    }";
    }
}
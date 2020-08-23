package jp4js.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
            "([(\"62.07\"), (\"155.28\"), (\"451.27\"), (\"61.60\"), (\"19.59\"), "+
                "(\"9.41\"), (\"21.75\"), (\"89.93\"), (\"194.61\"), (\"151.43\")])",
            "",
            "([(\"Johnathan Senouci\")])",
            "([(\"542.00\")])"
        };
    }
    
    @Override
    public String data() {
        ClassLoader loader = getClass().getClassLoader();
        File xmarkFile = new File(loader.getResource("xmark_sample.json").getFile());
        try {
            return new String(Files.readAllBytes(xmarkFile.toPath()));
        } catch (IOException e) {
            assert(false);
        }
        return null;
    }
}
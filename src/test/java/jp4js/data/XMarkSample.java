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
            "[[(\"62.07\"), (\"155.28\"), (\"451.27\"), (\"61.60\"), (\"19.59\"), (\"9.41\"), (\"21.75\"), (\"89.93\"), (\"194.61\"), (\"151.43\")]]",
            "[[(\"United States\"), (\"United States\"), (\"Denmark\"), (\"Uzbekistan\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\")]]",
            "[[(\"Huei Demke\"), (\"Daishiro Juric\"), (\"Kawon Unni\"), (\"Ewing Andrade\"), (\"Jamaludin Kleiser\"), (\"Eliana Ruemmler\"), (\"Bassem Nagasaki\"), (\"Mehrdad McCay\"), (\"Bassem Manderick\"), (\"Jarkko Nozawa\"), (\"Masanao Marsiglia\"), (\"Saul Schaap\"), (\"Kishor Monkewich\"), (\"Martti Halgason\"), (\"Laurian Grass\"), (\"Shooichi Oerlemans\"), (\"Uzi Atrawala\"), (\"Aloys Singleton\"), (\"Maha DuBourdieux\"), (\"Masaski Carrere\"), (\"Nestoras Gausemeier\"), (\"Yechezkel Calmet\"), (\"Slavian Usery\"), (\"Piere Schiex\"), (\"Shaoyun Morreau\")]]",
            "[[(\"1540.75\"), (\"398.65\"), (\"116.91\"), (\"174.84\"), (\"133.36\"), (\"24.53\")]]"
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
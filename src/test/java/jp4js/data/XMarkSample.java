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
            "$.open_auctions[*].reserve",
            "$.region..parlist..parlist"
        };
    }

    @Override
    public String[] res() {
        return new String[]{
            "[[(\"62.07\"), (\"155.28\"), (\"451.27\"), (\"61.60\"), (\"19.59\"), (\"9.41\"), (\"21.75\"), (\"89.93\"), (\"194.61\"), (\"151.43\")]]",
            "[[(\"United States\"), (\"United States\"), (\"Denmark\"), (\"Uzbekistan\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\"), (\"United States\")]]",
            "[[(\"Huei Demke\"), (\"Daishiro Juric\"), (\"Kawon Unni\"), (\"Ewing Andrade\"), (\"Jamaludin Kleiser\"), (\"Eliana Ruemmler\"), (\"Bassem Nagasaki\"), (\"Mehrdad McCay\"), (\"Bassem Manderick\"), (\"Jarkko Nozawa\"), (\"Masanao Marsiglia\"), (\"Saul Schaap\"), (\"Kishor Monkewich\"), (\"Martti Halgason\"), (\"Laurian Grass\"), (\"Shooichi Oerlemans\"), (\"Uzi Atrawala\"), (\"Aloys Singleton\"), (\"Maha DuBourdieux\"), (\"Masaski Carrere\"), (\"Nestoras Gausemeier\"), (\"Yechezkel Calmet\"), (\"Slavian Usery\"), (\"Piere Schiex\"), (\"Shaoyun Morreau\")]]",
            "[[(\"1540.75\"), (\"398.65\"), (\"116.91\"), (\"174.84\"), (\"133.36\"), (\"24.53\")]]",
            "[[([\"maintained peril rivall suddenly finds studies weary truth indulgence anatomy assisted imminent may excepted yonder aches regal\", \"<bold> friar prophetess </bold>spirits delays turning cassio finding unpractis steel sweets promises credulity err nym complete star greatly mope sorry experience virtues been offending bed drives faction learnt hurl eleven huge\", \"piece hours cruelly april league winged<keyword> tract element sails course placed fouler four plac joint </keyword>words blessing fortified loving forfeit doctor valiant crying wife county planet charge haughty precious alexander longboat bells lewd kingdoms knife giver frantic raz commend sit sovereignty engaged perceive its art alliance forge bestow perforce complete roof fie confident raging possible cassio teen crave park reign lords sounded our requite fourth confidence high\"]), ([\"sent fled bids oswald help answer artillery jealous hugh fingers gladly mows our craving<emph> preventions spurr edmund drunk how faction quickly bolingbroke painfully </emph>valorous line clasp cheek patchery encompassed honest after auspicious home engaged prompt mortimer bird dread jephthah prithee unfold deeds fifty goose either herald temperance coctus took sought fail each ado checking funeral thinks linger advantage bag ridiculous along accomplishment flower glittering school disguis portia beloved crown sheets garish rather forestall vaults doublet embassy ecstasy crimson rheum befall sin devout pedro little exquisite mote messenger lancaster hideous object arrows smites gently skins bora parting desdemona longing third throng character hat sov quit mounts true house field nearest lucrece tidings fought logotype eaten commanding treason censur ripe praises windsor temperate jealous made sleeve scorn throats fits uncape tended science preventions preventions high pipes reprieves<bold> sold </bold>marriage sampson safety distrust witch christianlike plague doubling visited with bleed offenders catching attendants<emph> cars livery stand </emph>denay<keyword> cimber paper admittance tread character </keyword>battlements seen dun irish throw redeem afflicts suspicion\", \"traduc barks twenty secure pursuit believing necessities longs mental lack further observancy uncleanly understanding vault athens lucius sleeps nor safety evidence repay whensoever senses proudest restraint love mouths slaves water athenian willingly hot grieves delphos pavilion sword indeed lepidus taking disguised proffer salt before educational streets things osw rey stern lap studies finger doomsday pots bounty famous manhood observe hopes unless languish<keyword> transformed nourish breeds north </keyword>\"])]]",
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
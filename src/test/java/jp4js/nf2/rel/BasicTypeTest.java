package jp4js.nf2.rel;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BasicTypeTest {
    
    @Test
    public void testDStringBasic01() {
        Document.DString.Instance dString01 = Document.createDString("Hello World!");
        Document.DString.Instance dString02 = Document.createDString("你好");
        Document.DString.Instance dString03 = Document.createDString("!@$%");
        assertThat(dString01.data()).isEqualTo("Hello World!");
        assertThat(dString02.data()).isEqualTo("你好");
        assertThat(dString03.data()).isEqualTo("!@$%");
    }

    @Test
    public void testDDoubleBasic01() {
        Document.DDouble.Instance dDouble01 = Document.createDDouble(1.01);
        Document.DDouble.Instance dDouble02 = Document.createDDouble(1.01e100);
        Document.DDouble.Instance dDouble03 = Document.createDDouble(-1.01e-100);

        assertThat(dDouble01.data()).isEqualTo(1.01);
        assertThat(dDouble02.data()).isEqualTo(1.01e100);
        assertThat(dDouble03.data()).isEqualTo(-1.01e-100);
    }

    @Test
    public void testDIntBasic01() {
        Document.DInt.Instance dDouble01 = Document.createDInt(1);
        Document.DInt.Instance dDouble02 = Document.createDInt((int)1e9);
        Document.DInt.Instance dDouble03 = Document.createDInt((int)-1e9);

        assertThat(dDouble01.data()).isEqualTo(1);
        assertThat(dDouble02.data()).isEqualTo(1000000000);
        assertThat(dDouble03.data()).isEqualTo(-1000000000);
    }

    @Test
    public void basic04_createDMapping() {
        Document.DMapping.Instance dMapping01 = Document.createDMapping();   
        dMapping01.put("dInt", Document.createDInt(100));
        dMapping01.put("dString", Document.createDString("h"));

        // System.out.println(dMapping01.toString());
        assertThat(dMapping01.toString()).isEqualTo(
            "{dInt=100, dString=\"h\"}"
        );
    }

    @Test
    public void basic05_createDList() {
        Document.DList.Instance dList01 = Document.createDList();
        dList01.add(Document.createDDouble(1.0));
        dList01.add(Document.createDInt(100));
        dList01.add(Document.createDString("good"));
        System.out.println(dList01.toString());

        assertThat(dList01.toString()).isEqualTo(
            "[1.0, 100, \"good\"]"
        );
    }
}
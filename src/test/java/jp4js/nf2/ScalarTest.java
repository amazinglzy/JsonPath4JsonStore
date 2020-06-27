package jp4js.nf2;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ScalarTest {
    
    @Test
    public void testDStringBasic01() {
        Scalar.DString.Instance dString01 = Scalar.createDString("Hello World!");
        Scalar.DString.Instance dString02 = Scalar.createDString("你好");
        Scalar.DString.Instance dString03 = Scalar.createDString("!@$%");
        assertThat(dString01.data()).isEqualTo("Hello World!");
        assertThat(dString02.data()).isEqualTo("你好");
        assertThat(dString03.data()).isEqualTo("!@$%");
    }

    @Test
    public void testDDoubleBasic01() {
        Scalar.DDouble.Instance dDouble01 = Scalar.createDDouble(1.01);
        Scalar.DDouble.Instance dDouble02 = Scalar.createDDouble(1.01e100);
        Scalar.DDouble.Instance dDouble03 = Scalar.createDDouble(-1.01e-100);

        assertThat(dDouble01.data()).isEqualTo(1.01);
        assertThat(dDouble02.data()).isEqualTo(1.01e100);
        assertThat(dDouble03.data()).isEqualTo(-1.01e-100);
    }

    @Test
    public void testDIntBasic01() {
        Scalar.DInt.Instance dDouble01 = Scalar.createDInt(1);
        Scalar.DInt.Instance dDouble02 = Scalar.createDInt((int)1e9);
        Scalar.DInt.Instance dDouble03 = Scalar.createDInt((int)-1e9);

        assertThat(dDouble01.data()).isEqualTo(1);
        assertThat(dDouble02.data()).isEqualTo(1000000000);
        assertThat(dDouble03.data()).isEqualTo(-1000000000);
    }

    @Test
    public void basic04_createDMapping() {
        Scalar.DMapping.Instance dMapping01 = Scalar.createDMapping();   
        dMapping01.put("dInt", Scalar.createDInt(100));
        dMapping01.put("dString", Scalar.createDString("h"));

        // System.out.println(dMapping01.toString());
        assertThat(dMapping01.toString()).isEqualTo(
            "{dInt=100, dString=\"h\"}"
        );
    }

    @Test
    public void basic05_createDList() {
        Scalar.DList.Instance dList01 = Scalar.createDList();
        dList01.add(Scalar.createDDouble(1.0));
        dList01.add(Scalar.createDInt(100));
        dList01.add(Scalar.createDString("good"));
        System.out.println(dList01.toString());

        assertThat(dList01.toString()).isEqualTo(
            "[1.0, 100, \"good\"]"
        );
    }
}
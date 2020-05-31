package jp4js.nf2;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BasicTypeTest {
    
    @Test
    public void testDStringBasic01() {
        BasicType.DString.Instance dString01 = BasicType.createDString("Hello World!");
        BasicType.DString.Instance dString02 = BasicType.createDString("你好");
        BasicType.DString.Instance dString03 = BasicType.createDString("!@$%");
        assertThat(dString01.data()).isEqualTo("Hello World!");
        assertThat(dString02.data()).isEqualTo("你好");
        assertThat(dString03.data()).isEqualTo("!@$%");
    }

    @Test
    public void testDDoubleBasic01() {
        BasicType.DDouble.Instance dDouble01 = BasicType.createDDouble(1.01);
        BasicType.DDouble.Instance dDouble02 = BasicType.createDDouble(1.01e100);
        BasicType.DDouble.Instance dDouble03 = BasicType.createDDouble(-1.01e-100);

        assertThat(dDouble01.data()).isEqualTo(1.01);
        assertThat(dDouble02.data()).isEqualTo(1.01e100);
        assertThat(dDouble03.data()).isEqualTo(-1.01e-100);
    }

    @Test
    public void testDIntBasic01() {
        BasicType.DInt.Instance dDouble01 = BasicType.createDInt(1);
        BasicType.DInt.Instance dDouble02 = BasicType.createDInt((int)1e9);
        BasicType.DInt.Instance dDouble03 = BasicType.createDInt((int)-1e9);

        assertThat(dDouble01.data()).isEqualTo(1);
        assertThat(dDouble02.data()).isEqualTo(1000000000);
        assertThat(dDouble03.data()).isEqualTo(-1000000000);
    }
}
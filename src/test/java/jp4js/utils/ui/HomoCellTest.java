package jp4js.utils.ui;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HomoCellTest {
    @Test
    public void testWidthHeight01() {
        HomoCell.SharedWidth w = new HomoCell.SharedWidth();
        HomoCell cell = new HomoCell("Good!", w);
        assertThat(cell.width()).isGreaterThan("Good!".length());
        assertThat(cell.height()).isGreaterThanOrEqualTo(1);

        w.update(100);
        assertThat(cell.width()).isEqualTo(100);
    }
}
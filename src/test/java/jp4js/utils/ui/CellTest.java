package jp4js.utils.ui;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {
    @Test
    public void testHeightWidth01() {
        Cell cell = new Cell("Good!");
        assertThat(cell.width()).isGreaterThan("Good!".length());
        assertThat(cell.height()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testDraw01() {
        Cell cell = new Cell("Bingo");
        CharMatrixDrawer drawer = new CharMatrixDrawer(cell.width());
        cell.draw(drawer, 0, 0);
        assertThat(drawer.toString()).isEqualTo(
            " Bingo "
        );
    }
}
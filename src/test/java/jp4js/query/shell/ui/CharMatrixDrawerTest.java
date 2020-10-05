package jp4js.query.shell.ui;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CharMatrixDrawerTest {
    @Test
    public void testDrawBasic01() {
        CharMatrixDrawer drawer = new CharMatrixDrawer(10);
        drawer.drawHorizontalChar(0, 1, '-', 5);
        drawer.draw(1, 0, '|');
        drawer.drawHorizontalString(1, 2, "age");
        drawer.draw(1, 6, '|');
        drawer.drawHorizontalChar(2, 1, '-', 5);

        System.out.println(drawer.toString());
        assertThat(drawer.toString()).isEqualTo(
            " -----    \n" + 
            "| age |   \n" +
            " -----    "
        );
    }
}
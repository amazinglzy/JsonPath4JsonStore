package jp4js.query.shell.layout;

import jp4js.query.shell.ui.CharMatrixDrawer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VerticalTest {
    @Test
    public void testWidthHeight01() {
        Vertical vertical = new Vertical.Builder() {{
            add(new ContainerMocker(5, 5));
            add(new ContainerMocker(8, 3));
            add(new ContainerMocker(9, 6));
            add(new ContainerMocker(4, 9));
        }}.build();
        assertThat(vertical.width()).isEqualTo(1 + 9 + 1);
        assertThat(vertical.height()).isEqualTo(1 + 5 + 1 + 8 + 1 + 9 + 1 + 4 + 1);
    }

    @Test
    public void testDraw01() {
        Vertical vertical = new Vertical.Builder() {{
            add(new ContainerMocker(2, 2));
            add(new ContainerMocker(3, 3));
            add(new ContainerMocker(3, 4));
        }}.build();
        CharMatrixDrawer drawer = new CharMatrixDrawer(vertical.width());
        vertical.draw(drawer, 0, 0);
        assertThat(drawer.toString()).isEqualTo(
            " ---- \n" +
            "|    |\n" +
            "|    |\n" +
            " ---- \n" +
            "|    |\n" +
            "|    |\n" +
            "|    |\n" +
            " ---- \n" +
            "|    |\n" +
            "|    |\n" +
            "|    |\n" +
            " ---- "
        );
    }
}
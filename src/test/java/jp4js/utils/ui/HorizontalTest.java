package jp4js.utils.ui;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HorizontalTest {
    @Test
    public void testWidthHeight01() {
        Horizontal horizontal = new Horizontal() {{
            add(new ContainerMocker(5, 5));
            add(new ContainerMocker(8, 3));
            add(new ContainerMocker(9, 6));
            add(new ContainerMocker(4, 9));
        }};
        assertThat(horizontal.height()).isEqualTo(1 + 9 + 1);
        assertThat(horizontal.width()).isEqualTo(1 + 5 + 1 + 3 + 1 + 6 + 1 + 9 + 1);
    }

    @Test
    public void testDraw01() {
        Horizontal horizontal = new Horizontal() {{
            add(new ContainerMocker(2, 2));
            add(new ContainerMocker(3, 3));
            add(new ContainerMocker(3, 4));
        }};
        CharMatrixDrawer drawer = new CharMatrixDrawer(horizontal.width());
        horizontal.draw(drawer, 0, 0);
        System.out.println(drawer.toString());
        assertThat(drawer.toString()).isEqualTo(
            " -- --- ---- \n" +
            "|  |   |    |\n" +
            "|  |   |    |\n" +
            "|  |   |    |\n" +
            " -- --- ---- "
        );
    }
}
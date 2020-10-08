package jp4js.utils.iter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiIterTest {
    @Test
    public void basic01_() {
        Iter<Integer> iter1 = new ArrayIter<Integer>(new ArrayList<Integer>(
            Arrays.asList(1, 4, 7)
        ));
        Iter<Integer> iter2 = new ArrayIter<Integer>(new ArrayList<Integer>(
            Arrays.asList(2, 3, 6)
        ));
        Iter<Integer> iter3 = new ArrayIter<Integer>(new ArrayList<Integer>(
            Arrays.asList(2, 5, 9)
        ));
        Iter<Integer> iter4 = new ArrayIter<Integer>(new ArrayList<Integer>(
            Arrays.asList(4, 8, 10)
        ));


        Iter<Integer> iter = new MultiIter<>(
            new ArrayList<Iter<Integer>>(Arrays.asList(iter1, iter2, iter3, iter4)),
            new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            }
        );

        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(1); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(2); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(2); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(3); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(4); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(4); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(5); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(6); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(7); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(8); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(9); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(10); iter.next();
        assertThat(iter.valid()).isFalse();
    }
}

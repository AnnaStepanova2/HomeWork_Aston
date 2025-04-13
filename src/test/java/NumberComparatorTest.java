import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    void testFirstGreater() {
        assertEquals("10 больше 5", NumberComparator.compare(10, 5));
    }

    @Test
    void testFirstLess() {
        assertEquals("5 меньше 10", NumberComparator.compare(5, 10));
    }

    @Test
    void testEqual() {
        assertEquals("10 равно 10", NumberComparator.compare(10, 10));
    }
}

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {
    @Test
    public void testFirstGreater() {
        assertEquals(NumberComparator.compare(10, 5), "10 больше 5");
    }

    @Test
    public void testFirstLess() {
        assertEquals(NumberComparator.compare(5, 10), "5 меньше 10");
    }

    @Test
    public void testEqual() {
        assertEquals(NumberComparator.compare(10, 10), "10 равно 10");
    }
}
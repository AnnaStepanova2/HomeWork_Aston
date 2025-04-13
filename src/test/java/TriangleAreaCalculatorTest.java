import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaCalculatorTest {
    @Test
    public void testAreaWithPositiveValues() {
        assertEquals(TriangleAreaCalculator.calculateArea(5, 4), 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAreaWithZeroBase() {
        TriangleAreaCalculator.calculateArea(0, 4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAreaWithNegativeHeight() {
        TriangleAreaCalculator.calculateArea(5, -4);
    }
}

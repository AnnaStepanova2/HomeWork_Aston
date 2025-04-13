import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaCalculatorTest {

    @Test
    void testAreaWithPositiveValues() {
        assertEquals(10.0, TriangleAreaCalculator.calculateArea(5, 4));
    }

    @Test
    void testAreaWithZeroBase() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(0, 4);
        });
    }

    @Test
    void testAreaWithNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(5, -4);
        });
    }
}
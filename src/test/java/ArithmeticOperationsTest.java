import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {

    @Test
    void testAdd() {
        assertEquals(15, ArithmeticOperations.add(10, 5));
    }

    @Test
    void testSubtract() {
        assertEquals(5, ArithmeticOperations.subtract(10, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(50, ArithmeticOperations.multiply(10, 5));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, ArithmeticOperations.divide(10, 5));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticOperations.divide(10, 0);
        });
    }
}
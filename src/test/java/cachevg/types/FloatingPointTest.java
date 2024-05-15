package cachevg.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatingPointTest {
    private static FloatingPoint float64t;
    private final static String MOCK_NUMBER = "123";

    @BeforeEach
    void init() {
        float64t = new FloatingPoint(MOCK_NUMBER, OffsetDateTime.MAX, OffsetDateTime.now());
    }

    @Test
    void testIntegerIncrement() {
        double expected = 123.01;

        double increment = 0.01;
        Number number = float64t.incrementByValue(increment);

        assertEquals(number, expected);
        assertEquals(float64t.value, String.valueOf(expected));
    }

    @Test
    void testIntegerDecrement() {
        double expected = 122.88;

        double decrement = 0.12;
        Number number = float64t.decrementByValue(decrement);

        assertEquals(number, expected);
        assertEquals(float64t.value, String.valueOf(expected));
    }
}

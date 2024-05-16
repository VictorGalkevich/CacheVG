package cachevg.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerValueTest {
    private static IntegerValue int64t;
    private final static String MOCK_NUMBER = "123";

    @BeforeEach
    void init() {
        int64t = new IntegerValue(MOCK_NUMBER, OffsetDateTime.MAX, OffsetDateTime.now());
    }

    @Test
    void testIntegerIncrement() {
        long expected = 1123;

        long increment = 1000;
        Number number = int64t.incrementByValue(increment);

        assertEquals(number, expected);
        assertEquals(int64t.value, String.valueOf(expected));
    }

    @Test
    void testIntegerDecrement() {
        long expected = 23;

        long decrement = 100;
        Number number = int64t.decrementByValue(decrement);

        assertEquals(number, expected);
        assertEquals(int64t.value, String.valueOf(expected));
    }

    @Test
    void testIsPowerOfTwo() {
        boolean expected = true;

        long decrement = -5;
        int64t.decrementByValue(decrement); //now stores 128

        boolean actual = int64t.isPowerOfTwo();

        assertEquals(actual, expected);
    }

    @Test
    void testIsNotPowerOfTwo() {
        boolean expected = false;

        boolean actual = int64t.isPowerOfTwo();

        assertEquals(actual, expected);
    }

    @Test
    void testIsEven() {
        boolean expected = true;

        long decrement = 1;
        int64t.decrementByValue(decrement); //now stores 122

        boolean actual = int64t.isEven();

        assertEquals(actual, expected);
    }

    @Test
    void testIsNotEven() {
        boolean expected = false;

        boolean actual = int64t.isEven();

        assertEquals(actual, expected);
    }

    @Test
    void testIsOdd() {
        boolean expected = true;

        boolean actual = int64t.isOdd();

        assertEquals(actual, expected);
    }

    @Test
    void testIsNotOdd() {
        boolean expected = false;

        long decrement = 1;
        int64t.decrementByValue(decrement); //now stores 122

        boolean actual = int64t.isOdd();

        assertEquals(actual, expected);
    }
}

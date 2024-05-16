package cachevg.types;

import cachevg.db.types.DefaultString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultStringTest {
    private static DefaultString string;
    private final static byte[] stringBytes = {0, 0, 1, 0, 1, 0, 1, 0};

    @BeforeEach
    void init() {
        string = new DefaultString(new String(stringBytes), OffsetDateTime.MAX, OffsetDateTime.now());
    }

    @Test
    void testLength() {
        int expected = 8;

        assertEquals(expected, string.length());
    }

    @Test
    void testSubstring() {
        String expectedWithoutBound = new String(new byte[]{0, 1, 0, 1, 0});
        String expectedWithBound = new String(new byte[]{0, 1, 0});

        int from = 3;
        int toWithoutBound = -1;
        int toWithBound = 5;

        String actualWithoutBound = string.substring(from, toWithoutBound);
        String actualWithBound = string.substring(from, toWithBound);

        assertEquals(expectedWithBound, actualWithBound);
        assertEquals(expectedWithoutBound, actualWithoutBound);
    }

    @Test
    void testSetRange() {
        String tail = "test";
        String expected = new String(new byte[]{0, 0, 1, 0}) + tail;
        int shift = 4;

        String actual = string.setRange(shift, tail);

        assertEquals(expected, actual);
    }

    @Test
    void testAppend() {
        String append = "test";
        String expected = new String(stringBytes) + append;

        String actual = string.append(append);

        assertEquals(expected, actual);
    }

    @Test
    void testPrepend() {
        String append = "test";
        String expected = append + new String(stringBytes);

        String actual = string.prepend(append);

        assertEquals(expected, actual);
    }

    @Test
    void testReverse() {
        String expected = new String(new byte[]{0, 1, 0, 1, 0, 1, 0, 0});

        String actual = string.reverse();
        assertEquals(expected, actual);
    }

    @Test
    void testCountZeroes() {
        int expected = 61;

        int actual = string.countZeroes();
        assertEquals(expected, actual);
    }

    @Test
    void testCountOnes() {
        int expected = 3;

        int actual = string.countOnes();
        assertEquals(expected, actual);
    }

    @Test
    void testSetBit() {
        String expected = new String(new byte[]{1, 0, 1, 0, 1, 0, 1, 0});

        String actual = string.setBit(7, '1');
        assertEquals(expected, actual);
    }

    @Test
    void testGetBit() {
        char expectedZero = '0';
        char expectedOne = '1';

        char bitZero = string.getBit(8);
        char bitOne = string.getBit(23);
        assertEquals(expectedZero, bitZero);
        assertEquals(expectedOne, bitOne);
    }

    @Test
    void testFlip() {
        String expected = new String(new byte[]{1, 0, 1, 0, 1, 0, 1, 0});

        String actual = string.flip(7);
        assertEquals(expected, actual);
    }

    @Test
    void testNot() {
        String expected = new String(new byte[]{~0, ~0, ~1, ~0, ~1, ~0, ~1, ~0});

        String actual = string.not();
        assertEquals(expected, actual);
    }

    @Test
    void testConjunction() {
        String actual = string.conjunction(new String(stringBytes));

        assertEquals(actual, string.value);
    }

    @Test
    void testDisjunction() {
        byte[] bytes = new byte[8];
        Arrays.fill(bytes, (byte) ~0);
        String expected = new String(bytes);

        String actual = string.disjunction(string.not());

        assertEquals(expected, actual);
    }

    @Test
    void testXor() {
        byte[] bytes = new byte[8];
        Arrays.fill(bytes, (byte) 0);
        String expected = new String(bytes);

        String actual = string.xor(string.value);

        assertEquals(expected, actual);
    }
}

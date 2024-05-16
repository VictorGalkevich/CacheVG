package cachevg.db.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnknownCommandProcessorTest {

    @Test
    void testEmptyInput() {
        String[] splitted = new String[0];

        String expected = "Empty input";

        String actual = new UnknownCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testCommandIsNotSupported() {
        String[] splitted = "TEST".split(" ");

        String expected = "Command TEST is not supported";

        String actual = new UnknownCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }
}

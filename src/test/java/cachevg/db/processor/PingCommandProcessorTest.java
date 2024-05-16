package cachevg.db.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PingCommandProcessorTest {

    @Test
    void testInvalidCommand() {
        String[] splitted = new String[0];

        String expected = "Empty input";

        String actual = new PingCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testPingCommand() {
        String[] splitted = "PONG".split(" ");

        String expected = "PONG";

        String actual = new PingCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }
}

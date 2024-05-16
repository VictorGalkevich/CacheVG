package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeysCommandProcessorTest {

    @BeforeAll
    static void init() {
        InitialStorage.instance().put("key:1:string", "value");
        InitialStorage.instance().put("key:3:string", "hello");
        InitialStorage.instance().put("key:2:notastring", "123");
        InitialStorage.instance().put("key:1:integer", "1");
        InitialStorage.instance().put("key:1:float", "1.2");
    }

    @AfterAll
    static void after() {
        InitialStorage.instance().clear();
    }

    @Test
    void testKeysDefault() {
        String query = "KEYS";
        String[] splitted = query.split(" ");

        String expected = """
                1) key:1:integer
                2) key:2:notastring
                3) key:1:string
                4) key:3:string
                5) key:1:float
                        """;

        String actual = new KeysCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testKeysWithPatternOne() {
        String query = "KEYS key:*:string";
        String[] splitted = query.split(" ");

        String expected = """
                1) key:1:string
                2) key:3:string
                        """;

        String actual = new KeysCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testKeysWithPatternTwo() {
        String query = "KEYS key:1:*";
        String[] splitted = query.split(" ");

        String expected = """
                1) key:1:integer
                2) key:1:string
                3) key:1:float
                """;

        String actual = new KeysCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testKeysWithPatternThree() {
        String query = "KEYS *";
        String[] splitted = query.split(" ");

        String expected = """
                1) key:1:integer
                2) key:2:notastring
                3) key:1:string
                4) key:3:string
                5) key:1:float
                        """;

        String actual = new KeysCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testInvalidCommand() {
        String[] splitted = new String[0];

        String expected = "Command format must be: KEYS ([pattern]), instead got: ";

        String actual = new KeysCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }
}

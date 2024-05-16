package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCommandProcessorTest {
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
    void testRemoveDefault() {
        String query = "GET key:1:integer";
        String[] splitted = query.split(" ");

        String expected = "1";

        String actual = new GetCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testRemoveKeyThatDoesntExist() {
        String query = "GET key:2:integer";
        String[] splitted = query.split(" ");

        String expected = "nil";

        String actual = new GetCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testEmptyInput() {
        String[] splitted = new String[0];

        String expected = "Command format must be: GET [key], instead got: ";

        String actual = new GetCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testSingleInput() {
        String[] splitted = "GET".split(" ");

        String expected = "Command format must be: GET [key], instead got: GET";

        String actual = new GetCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }
}
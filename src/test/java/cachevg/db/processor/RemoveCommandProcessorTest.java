package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveCommandProcessorTest {

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
        String query = "REMOVE key:1:integer";
        String[] splitted = query.split(" ");

        String expected = "1";

        String actual = new RemoveCommandProcessor().process(splitted);

        assertEquals(expected, actual);
        assertThat(InitialStorage.instance().keys()).hasSize(4);
    }

    @Test
    void testRemoveKeyThatDoesntExist() {
        String query = "REMOVE key:2:integer";
        String[] splitted = query.split(" ");

        String expected = "nil";

        String actual = new RemoveCommandProcessor().process(splitted);

        assertEquals(expected, actual);
        assertThat(InitialStorage.instance().keys()).hasSize(4);
    }

    @Test
    void testEmptyInput() {
        String[] splitted = new String[0];

        String expected = "Command format must be: REMOVE [key], instead got: ";

        String actual = new RemoveCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testSingleInput() {
        String[] splitted = "REMOVE".split(" ");

        String expected = "Command format must be: REMOVE [key], instead got: REMOVE";

        String actual = new RemoveCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }
}

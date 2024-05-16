package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutCommandProcessorTest {

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
    void testPutDefault() {
        String query = "PUT key:1:integer somevalue";
        String[] splitted = query.split(" ");

        String expected = "somevalue";

        String actual = new PutCommandProcessor().process(splitted);

        assertThat(InitialStorage.instance().keys()).hasSize(5);
        assertEquals(expected, actual);
    }

    @Test
    void testRemoveKeyThatDoesntExist() {
        String query = "PUT key:5:integer someothervalue";
        String[] splitted = query.split(" ");

        String expected = "someothervalue";

        String actual = new PutCommandProcessor().process(splitted);

        assertThat(InitialStorage.instance().keys()).hasSize(6);
        assertEquals(expected, actual);
    }

    @Test
    void testEmptyInput() {
        String[] splitted = new String[0];

        String expected = "Command format must be: PUT [key] [value], instead got: ";

        String actual = new PutCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testSingleInput() {
        String[] splitted = "PUT".split(" ");

        String expected = "Command format must be: PUT [key] [value], instead got: PUT";

        String actual = new PutCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }

    @Test
    void testTwoWordsInput() {
        String[] splitted = "PUT key1".split(" ");

        String expected = "Command format must be: PUT [key] [value], instead got: PUT key1";

        String actual = new PutCommandProcessor().process(splitted);

        assertEquals(expected, actual);
    }
}

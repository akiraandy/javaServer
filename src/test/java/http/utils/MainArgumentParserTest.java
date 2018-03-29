package http.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainArgumentParserTest {

    public static final String FLAG = "-f";
    public static final String NAME = "ArgumentName";
    public static final String VALUE = "ArgumentValue";
    MainArgumentParser parser;
    String[] args;

    @BeforeEach
    void setUp() {
        parser = new MainArgumentParser();
    }

    @Test
    void ReturnsArguments() throws Exception {
        Map<String, String> parsedArguments = parser.parse(args);

        assertEquals(VALUE, parsedArguments.get(NAME));
    }

    @Test
    void HandlesUnregiesteredFlags() {
        parser.addFlag(FLAG, NAME);


        assertThrows(Exception.class, () -> parser.parse(args));
    }


    @Test
    void ReturnsDefaultValueIfNoFlagInArguments() throws Exception {
        parser.addFlag(FLAG, NAME, VALUE);

        args = new String[0];

        Map<String, String> parsedArguments = parser.parse(args);

        assertEquals(VALUE, parsedArguments.get(NAME));
    }
}
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 02/01/2017.
 */
public class TestTextDisplay {


    private static Map<String, String> tests;

    @BeforeAll
    public static void readTestCases(){
        tests = TestUtils.readTestFile("data01.txt");

    }

    @Test
    public void testEmptyTeste4x4isReadCorrectly() {
        assertEquals(
                "...." + System.lineSeparator() +
                        "...." + System.lineSeparator() +
                        "...." + System.lineSeparator() +
                        "...." + System.lineSeparator(), tests.get("e4x4"));
    }

    @Test
    public void testEmptyTeste3x5isReadCorrectly() {
        assertEquals(
                "....." + System.lineSeparator() +
                        "....." + System.lineSeparator() +
                        "....." + System.lineSeparator(), TestUtils.getTestCase("data01.txt", "e3x5"));
    }

    @Test
    public void testEmptyTeste5x5isReadCorrectly(){

        assertEquals(
                "....." + System.lineSeparator() +
                         "....." + System.lineSeparator() +
                         "....." + System.lineSeparator() +
                         "....." + System.lineSeparator() +
                         "....." + System.lineSeparator(), TestUtils.getTestCase("data01.txt","e5x5") );
    }
}

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Stephen West on 02/01/2017.
 */
public class TestTextDisplay {

    private static final String PATH = "test//";
    private static final String END = "end";
    private static final String TESTCASE = "testcase:";
    private static Map<String, String> tests;

    @BeforeAll
    public static void readTestCases(){
        tests = new HashMap<>();
        String key = "";
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(PATH+"data01.txt"))){
            String line;

            while ((line = br.readLine() )!= null){

                System.out.println(line);
                if (line.startsWith(TESTCASE)){

                    key = line.substring(TESTCASE.length());
                }else if (line.startsWith(END)) {
                    tests.put(key,sb.toString());
                }else {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testEmptyTextDisply(){
        System.out.println(tests.get("e4x4"));
        assertTrue(true);
    }
}

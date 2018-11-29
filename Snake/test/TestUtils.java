import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen West on 29/11/2018.
 */
public class TestUtils {

    private static final String PATH = "test//";
    private static final String END = "end";
    private static final String TESTCASE = "testcase:";

    public static String getTestCase(String testFile, String testCase){
        Map<String, String> tests = readTestFile(testFile);
        String testCaseOutput = tests.get(testCase);
        return testCaseOutput;
    }

    public static Map<String, String> readTestFile(String testFile) {
        Map<String, String> tests = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(PATH+ testFile))){
            String line;
            String key = "";
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine() )!= null) {

                if (line.startsWith(TESTCASE)) {
                    key = line.substring(TESTCASE.length());
                } else if (line.startsWith(END)) {
                    tests.put(key, sb.toString());
                    sb = new StringBuilder();
                } else {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tests;
    }
}

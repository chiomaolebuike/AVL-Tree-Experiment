/**
 * A utility class to test and compare queries between two files.
 * 
 * This application reads queries from a test file and checks if each query
 * exists in a knowledge base (KB) query file. It prints whether each query
 * is a match or not.
 * 
 * <p>The application performs the following steps:
 * <ul>
 *   <li>Reads queries from a test file</li>
 *   <li>Compares each test query against queries in a knowledge base file</li>
 *   <li>Prints whether a match is found for each query</li>
 * </ul>
 * </p>
 * 
 * @author Chioma Olebuike
 * @version 1.0
 */
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests query matching between a test query file and a KB query file.
 * Optimised version: loads KB queries once instead of re-reading for each test.
 */
public class TestGenericsKbAVLApp {

    public static void main(String[] args) {
        String testFilePath = "TestQueries.txt";
        String kbFilePath = "GenericsKB-queries.txt";

        try {
            // Load KB queries once
            List<String> kbQueries = loadLines(kbFilePath);

            // Load test queries and compare
            try (BufferedReader testReader = new BufferedReader(new FileReader(testFilePath))) {
                String testQuery;
                while ((testQuery = testReader.readLine()) != null) {
                    testQuery = testQuery.trim();
                    if (testQuery.isEmpty()) continue;

                    if (kbQueries.contains(testQuery)) {
                        System.out.println("Match: " + testQuery);
                    } else {
                        System.out.println("Not a match: " + testQuery);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Utility: reads all non-empty trimmed lines from a file into a list */
    private static List<String> loadLines(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) lines.add(line);
            }
        }
        return lines;
    }
}

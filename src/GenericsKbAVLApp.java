/**
 * An application for managing a knowledge base using an AVL Tree.
 * 
 * @author Chioma Olebuike
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;

/**
 * Application for loading a knowledge base into an AVL tree
 * and performing queries.
 */
public class GenericsKbAVLApp {

    // Ensure your AVLTree is generic: AVLTree<Statement>
    private AVLTree<Statement> avl = new AVLTree<>();

    /** Loads "term \t sentence \t confidence" lines into the AVL tree. */
    public void loadStatement(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    String term = parts[0].trim();
                    String sentence = parts[1].trim();
                    double confidence = Double.parseDouble(parts[2].trim());

                    avl.insert(new Statement(term, sentence, confidence));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Processes queries and writes results to an output file. */
    public void handleQuery(String queriesFilePath, String outputFilePath) {
        try (BufferedReader queryReader = new BufferedReader(new FileReader(queriesFilePath));
             PrintStream fileOut = new PrintStream(new FileOutputStream(outputFilePath))) {

            String query;
            while ((query = queryReader.readLine()) != null) {
                query = query.trim();
                if (query.isEmpty()) continue;

                // Search AVL by key (term)
                BinaryTreeNode<Statement> foundNode = avl.find(query);

                if (foundNode != null) {
                    fileOut.println("Term found: " + query);
                } else {
                    fileOut.println("Term not found: " + query);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print instrumentation metrics
        avl.printMetrics();
    }

    public static void main(String[] args) {
        GenericsKbAVLApp app = new GenericsKbAVLApp();
        app.loadStatement("GenericsKB.txt");
        app.handleQuery("GenericsKB-queries.txt", "output.txt");
    }
}

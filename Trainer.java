import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private List<String> spamExamples;
    private List<String> hamExamples;
    private final String SPAM_FILE = "spam.txt";
    private final String HAM_FILE = "ham.txt";

    public Trainer() {
        spamExamples = new ArrayList<>();
        hamExamples = new ArrayList<>();
        loadExamples();
    }

    private void loadExamples() {
        spamExamples = loadFromFile(SPAM_FILE);
        hamExamples = loadFromFile(HAM_FILE);
    }

    private List<String> loadFromFile(String filename) {
        List<String> examples = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating " + filename);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                examples.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename);
        }
        return examples;
    }

    private void saveToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content.toLowerCase());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to " + filename);
        }
    }

    public void trainSpam(String text) {
        spamExamples.add(text.toLowerCase());
        saveToFile(SPAM_FILE, text);
    }

    public void trainHam(String text) {
        hamExamples.add(text.toLowerCase());
        saveToFile(HAM_FILE, text);
    }

    public List<String> getSpamExamples() {
        return spamExamples;
    }

    public List<String> getHamExamples() {
        return hamExamples;
    }
}

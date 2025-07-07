import java.io.*;
import java.util.*;

public class SpamFilter {
    private Map<String, Integer> spamWords = new HashMap<>();
    private Map<String, Integer> hamWords = new HashMap<>();
    private Set<String> vocabulary = new HashSet<>();

    private int totalSpamMessages = 0;
    private int totalHamMessages = 0;
    private int totalSpamWordCount = 0;
    private int totalHamWordCount = 0;

    public void train(String spamFile, String hamFile) {
        readFile(spamFile, spamWords, true);
        readFile(hamFile, hamWords, false);
    }

    private void readFile(String fileName, Map<String, Integer> wordMap, boolean isSpam) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isSpam) totalSpamMessages++;
                else totalHamMessages++;

                String[] words = tokenize(line);

                for (String word : words) {
                    vocabulary.add(word);
                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);

                    if (isSpam) totalSpamWordCount++;
                    else totalHamWordCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }
    }

    public String classify(String input) {
        String[] words = tokenize(input);
        int vocabSize = vocabulary.size();

        double spamProb = Math.log((double) totalSpamMessages / (totalSpamMessages + totalHamMessages));
        double hamProb = Math.log((double) totalHamMessages / (totalSpamMessages + totalHamMessages));

        for (String word : words) {
            int spamCount = spamWords.getOrDefault(word, 0);
            int hamCount = hamWords.getOrDefault(word, 0);

            double wordGivenSpam = Math.log((spamCount + 1.0) / (totalSpamWordCount + vocabSize));
            double wordGivenHam = Math.log((hamCount + 1.0) / (totalHamWordCount + vocabSize));

            spamProb += wordGivenSpam;
            hamProb += wordGivenHam;
        }

        return spamProb > hamProb ? "Spam" : "Not Spam";
    }

    private String[] tokenize(String line) {
        return line.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
    }
}

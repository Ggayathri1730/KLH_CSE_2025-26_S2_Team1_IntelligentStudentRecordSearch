package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CorpusLoader {

    private static final int FUZZY_THRESHOLD = 2;

    private static class StudentRecord {
        String fileName;
        String content;
        String rollNumber = "Not available";
        String name = "Not available";

        StudentRecord(File file, String content) {
            this.fileName = file.getName();
            this.content = content;

            for (String line : content.split("\\R")) {
                String trimmed = line.trim();

                if (trimmed.startsWith("Roll Number:")) {
                    rollNumber =
                            trimmed.substring("Roll Number:".length()).trim();
                } else if (trimmed.startsWith("Name:")) {
                    name =
                            trimmed.substring("Name:".length()).trim();
                }
            }
        }
    }

    private static class FuzzyMatch {
        String candidate;
        int distance;

        FuzzyMatch(String candidate, int distance) {
            this.candidate = candidate;
            this.distance = distance;
        }
    }

    private static void collectTextFiles(
            File folder, List<File> textFiles) {

        File[] files = folder.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                collectTextFiles(file, textFiles);
            } else if (file.isFile()
                    && file.getName().toLowerCase(Locale.ROOT)
                    .endsWith(".txt")) {
                textFiles.add(file);
            }
        }
    }

    private static String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line)
                       .append(System.lineSeparator());
            }
        }

        return content.toString();
    }

    private static List<StudentRecord> loadRecords(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println(
                    "Error: Corpus folder not found: " + folderPath);
            System.out.println(
                    "Please check the corpus folder path.");
            return null;
        }

        List<File> files = new ArrayList<>();
        collectTextFiles(folder, files);

        if (files.isEmpty()) {
            System.out.println(
                    "No .txt student records found in the corpus.");
            return null;
        }

        List<StudentRecord> records = new ArrayList<>();

        for (File file : files) {
            try {
                records.add(
                        new StudentRecord(file, readFile(file)));
            } catch (IOException e) {
                System.out.println(
                        "Could not read " + file.getName()
                        + ": " + e.getMessage());
            }
        }

        return records;
    }

    // =========================================================
    // OPTION 1: NORMAL SEARCH USING KMP
    // =========================================================

    public static void searchCorpus(
            String folderPath, String pattern) {

        if (pattern == null || pattern.trim().isEmpty()) {
            System.out.println("Search pattern cannot be empty.");
            return;
        }

        List<StudentRecord> records = loadRecords(folderPath);

        if (records == null) {
            return;
        }

        String searchPattern = pattern.toLowerCase(Locale.ROOT);
        int matchCount = 0;

        System.out.println("\n========== KMP SEARCH RESULTS ==========");

        for (StudentRecord record : records) {
            String text = record.content.toLowerCase(Locale.ROOT);

            if (KMP.search(text, searchPattern)) {
                matchCount++;

                printRecord(record, matchCount);
            }
        }

        printSummary(matchCount, "Exact KMP");
        SearchHistory.saveSearch(pattern, matchCount);
    }

    // =========================================================
    // NAIVE PATTERN MATCHING: SEARCH THE ENTIRE CORPUS
    // =========================================================

    public static void naiveSearchCorpus(
            String folderPath, String pattern) {

        if (pattern == null || pattern.trim().isEmpty()) {
            System.out.println("Search pattern cannot be empty.");
            return;
        }

        List<StudentRecord> records = loadRecords(folderPath);

        if (records == null) {
            return;
        }

        String searchPattern = pattern.toLowerCase(Locale.ROOT);
        int matchCount = 0;

        System.out.println(
                "\n===== NAIVE PATTERN MATCHING RESULTS =====");

        for (StudentRecord record : records) {
            String text = record.content.toLowerCase(Locale.ROOT);

            // NaivePatternMatching.search returns the first index,
            // or -1 if the pattern is not present.
            if (NaivePatternMatching.search(
                    text, searchPattern) >= 0) {

                matchCount++;

                printRecord(record, matchCount);
            }
        }

        printSummary(matchCount, "Naive Pattern Matching");
        SearchHistory.saveSearch(pattern, matchCount);
    }

    // =========================================================
    // SHARED STUDENT RECORD DISPLAY
    // =========================================================

    private static void printRecord(
            StudentRecord record, int matchCount) {

        System.out.println("\nMatch " + matchCount);
        System.out.println("File: " + record.fileName);
        System.out.println("Roll Number: " + record.rollNumber);
        System.out.println("Name: " + record.name);
        System.out.println("Complete Student Record:");
        System.out.println(record.content);
        System.out.println("----------------------------------------");
    }

    // =========================================================
    // OPTION 2: FUZZY SEARCH USING EDIT DISTANCE
    // =========================================================

    public static void fuzzySearchCorpus(
            String folderPath, String pattern) {

        if (pattern == null || pattern.trim().isEmpty()) {
            System.out.println("Search pattern cannot be empty.");
            return;
        }

        List<StudentRecord> records = loadRecords(folderPath);

        if (records == null) {
            return;
        }

        String query = normalize(pattern);
        int matchCount = 0;

        System.out.println("\n======= FUZZY SEARCH RESULTS =======");
        System.out.println("Query: " + pattern);
        System.out.println(
                "Maximum edit distance: " + FUZZY_THRESHOLD);

        for (StudentRecord record : records) {
            FuzzyMatch match =
                    findClosestMatch(query, record.content);

            if (match != null
                    && match.distance <= FUZZY_THRESHOLD) {

                matchCount++;

                System.out.println("\nMatch " + matchCount);
                System.out.println("File: " + record.fileName);
                System.out.println("Roll Number: " + record.rollNumber);
                System.out.println("Name: " + record.name);
                System.out.println("Closest match: " + match.candidate);
                System.out.println("Edit distance: " + match.distance);
                System.out.println("Complete Student Record:");
                System.out.println(record.content);
                System.out.println("------------------------------------");
            }
        }

        printSummary(matchCount, "Fuzzy");
        SearchHistory.saveSearch(pattern, matchCount);
    }

    // =========================================================
    // FUZZY SEARCH HELPERS
    // =========================================================

    private static String normalize(String value) {
        return value.trim()
                    .toLowerCase(Locale.ROOT)
                    .replaceAll("[^\\p{L}\\p{N}]+", " ")
                    .trim();
    }

    private static FuzzyMatch findClosestMatch(
            String query, String content) {

        if (query.isEmpty()) {
            return null;
        }

        String normalizedContent = normalize(content);

        // Exact phrase match always has distance zero.
        if (containsPhrase(normalizedContent, query)) {
            return new FuzzyMatch(query, 0);
        }

        String[] queryWords = query.split("\\s+");
        String[] words = normalizedContent.split("\\s+");

        // Very short queries are exact-only to avoid false positives.
        if (query.length() < 3) {
            return null;
        }

        FuzzyMatch best = null;

        // For a single word, compare against individual words only.
        if (queryWords.length == 1) {
            for (String word : words) {
                if (word.isEmpty()) {
                    continue;
                }

                int distance =
                        EditDistance.calculate(query, word);

                if (best == null || distance < best.distance) {
                    best = new FuzzyMatch(word, distance);
                }

                if (distance == 0) {
                    return best;
                }
            }

            return best;
        }

        // For a phrase, compare adjacent groups with similar word counts.
        int minWords = Math.max(1, queryWords.length - 1);
        int maxWords = queryWords.length + 1;

        for (int start = 0; start < words.length; start++) {
            for (int count = minWords;
                    count <= maxWords
                    && start + count <= words.length;
                    count++) {

                StringBuilder candidate = new StringBuilder();

                for (int j = start; j < start + count; j++) {
                    if (candidate.length() > 0) {
                        candidate.append(' ');
                    }

                    candidate.append(words[j]);
                }

                String phrase = candidate.toString();

                int distance =
                        EditDistance.calculate(query, phrase);

                if (best == null || distance < best.distance) {
                    best = new FuzzyMatch(phrase, distance);
                }

                if (distance == 0) {
                    return best;
                }
            }
        }

        return best;
    }

    private static boolean containsPhrase(
            String normalizedContent, String query) {

        return (" " + normalizedContent + " ")
                .contains(" " + query + " ");
    }

    // =========================================================
    // SHARED SEARCH SUMMARY
    // =========================================================

    private static void printSummary(
            int count, String searchType) {

        System.out.println("\n========================================");

        if (count == 0) {
            System.out.println(
                    "No matching student records found.");
        } else {
            System.out.println(
                    "Total matching student records: " + count);
        }

        System.out.println("Search type: " + searchType);
        System.out.println("========================================");
    }
}

package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchHistory {

    private static final String HISTORY_FILE =
            "results/search_history.txt";

    public static void saveSearch(String query, int matchCount) {

        File resultsFolder = new File("results");

        // Create results folder if it does not exist
        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String dateTime =
                LocalDateTime.now().format(formatter);

        try (FileWriter writer =
                     new FileWriter(HISTORY_FILE, true)) {

            writer.write("========================================\n");
            writer.write("Search Query      : " + query + "\n");
            writer.write("Matching Students : " + matchCount + "\n");
            writer.write("Date and Time     : " + dateTime + "\n");
            writer.write("========================================\n\n");

        } catch (IOException e) {
            System.out.println("Error saving search history.");
        }
    }

    public static void viewHistory() {

        File file = new File(HISTORY_FILE);

        if (!file.exists()) {
            System.out.println("\nNo search history available.");
            return;
        }

        System.out.println("\n========== SEARCH HISTORY ==========\n");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading search history.");
        }
    }

    public static void clearHistory() {

        try (FileWriter writer =
                     new FileWriter(HISTORY_FILE, false)) {

            System.out.println("\nSearch history cleared successfully.");

        } catch (IOException e) {
            System.out.println("Error clearing search history.");
        }
    }
}

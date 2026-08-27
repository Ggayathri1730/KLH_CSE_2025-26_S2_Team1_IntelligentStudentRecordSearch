package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CorpusLoader {

    public static void searchCorpus(String folderPath, String pattern) {

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("Corpus folder not found.");
            return;
        }

        System.out.println("\n======================================");
        System.out.println("Search Pattern: " + pattern);
        System.out.println("======================================");

        int matchCount = 0;

        for (File file : files) {

            if (file.isFile() && file.getName().endsWith(".txt")) {

                StringBuilder content = new StringBuilder();

                try (BufferedReader reader =
                             new BufferedReader(new FileReader(file))) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        content.append(line).append(" ");
                    }

                    // Case-insensitive KMP search
                    boolean found = KMP.search(
                            content.toString().toLowerCase(),
                            pattern.toLowerCase()
                    );

                    if (found) {

                        matchCount++;

                        System.out.println("\n========== MATCH FOUND ==========\n");

                        // Print complete student details
                        try (BufferedReader displayReader =
                                     new BufferedReader(new FileReader(file))) {

                            while ((line = displayReader.readLine()) != null) {
                                System.out.println(line);
                            }
                        }

                        System.out.println("\n=================================");
                    }

                } catch (IOException e) {
                    System.out.println("Error reading: " + file.getName());
                }
            }
        }

        System.out.println("\n======================================");
        System.out.println("Total Matching Records: " + matchCount);
        System.out.println("======================================");
    }
}
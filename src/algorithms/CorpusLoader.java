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

                    boolean found = KMP.search(
                            content.toString(),
                            pattern
                    );

                    if (found) {

                        matchCount++;

                        System.out.println("\n========== MATCH FOUND ==========");
                        System.out.println("File: " + file.getName());
                        System.out.println("=================================");

                        // Display complete student record
                        try (BufferedReader displayReader =
                                     new BufferedReader(new FileReader(file))) {

                            while ((line = displayReader.readLine()) != null) {
                                System.out.println(line);
                            }
                        }

                        System.out.println("=================================");
                    }

                } catch (IOException e) {

                    System.out.println(
                            "Error reading: " + file.getName()
                    );
                }
            }
        }

        System.out.println("\n======================================");
        System.out.println("Total Matching Records: " + matchCount);
        System.out.println("======================================");
    }
}
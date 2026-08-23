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

        System.out.println("Search Pattern: " + pattern);
        System.out.println("--------------------------------");

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
                        System.out.println(
                                "Match found in: " + file.getName()
                        );
                    }

                } catch (IOException e) {
                    System.out.println(
                            "Error reading: " + file.getName()
                    );
                }
            }
        }
    }
}

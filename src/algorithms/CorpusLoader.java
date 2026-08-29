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

        int matchCount = 0;

        String searchPattern = pattern.toLowerCase();

        System.out.println("\n==============================================");
        System.out.println("         INTELLIGENT SEARCH RESULT");
        System.out.println("==============================================");
        System.out.println("Search Query: " + pattern);

        for (File file : files) {

            if (file.isFile() && file.getName().endsWith(".txt")) {

                StringBuilder content = new StringBuilder();

                try (BufferedReader reader =
                             new BufferedReader(new FileReader(file))) {

                    String line;

                    // Read complete file content
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    // Search the complete record using KMP
                    boolean found = KMP.search(
                            content.toString().toLowerCase(),
                            searchPattern
                    );

                    if (found) {

                        matchCount++;

                        String rollNumber = "";
                        String name = "";
                        StringBuilder matchingFields = new StringBuilder();
                        int matchingFieldCount = 0;

                        // Read the record again to extract details
                        try (BufferedReader displayReader =
                                     new BufferedReader(new FileReader(file))) {

                            while ((line = displayReader.readLine()) != null) {

                                String lowerLine = line.toLowerCase();

                                // Store student Roll Number
                                if (line.startsWith("Roll Number")) {
                                    rollNumber = line;
                                }

                                // Store student Name
                                if (line.startsWith("Name")) {
                                    name = line;
                                }

                                // Check each field using KMP
                                if (KMP.search(lowerLine, searchPattern)) {

                                    // Don't include Roll Number and Name here
                                    // because they are shown in Student Identity
                                    if (!line.startsWith("Roll Number")
                                            && !line.startsWith("Name")) {

                                        matchingFields.append("> ")
                                                .append(line)
                                                .append("\n");

                                        matchingFieldCount++;
                                    }
                                }
                            }
                        }

                        System.out.println("\nMatching Student Record #"
                                + matchCount);

                        System.out.println("\n----------- STUDENT IDENTITY -----------");
                        System.out.println(rollNumber);
                        System.out.println(name);
                        System.out.println("----------------------------------------");

                        System.out.println("\nMATCHING INFORMATION");
                        System.out.println("----------------------------------------");

                        // Display fields containing the search pattern
                        if (matchingFields.length() > 0) {

                            System.out.print(matchingFields);

                        } else {

                            // If the search matches only Roll Number
                            if (KMP.search(
                                    rollNumber.toLowerCase(),
                                    searchPattern)) {

                                System.out.println("> " + rollNumber);
                                matchingFieldCount++;
                            }

                            // If the search matches only Name
                            if (KMP.search(
                                    name.toLowerCase(),
                                    searchPattern)) {

                                System.out.println("> " + name);
                                matchingFieldCount++;
                            }
                        }

                        System.out.println("\nMatch Summary: \""
                                + pattern + "\" found in "
                                + matchingFieldCount + " field(s)");

                        System.out.println("==============================================");
                    }

                } catch (IOException e) {

                    System.out.println(
                            "Error reading: " + file.getName()
                    );
                }
            }
        }

        System.out.println("\nTotal Matching Students: " + matchCount);
        System.out.println("==============================================");

        // Save search query and result count to search history
        SearchHistory.saveSearch(pattern, matchCount);
    }
}package algorithms;

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

        int matchCount = 0;

        String searchPattern = pattern.toLowerCase();

        System.out.println("\n==============================================");
        System.out.println("         INTELLIGENT SEARCH RESULT");
        System.out.println("==============================================");
        System.out.println("Search Query: " + pattern);

        for (File file : files) {

            if (file.isFile() && file.getName().endsWith(".txt")) {

                StringBuilder content = new StringBuilder();

                try (BufferedReader reader =
                             new BufferedReader(new FileReader(file))) {

                    String line;

                    // Read complete file content
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    // Search the complete record using KMP
                    boolean found = KMP.search(
                            content.toString().toLowerCase(),
                            searchPattern
                    );

                    if (found) {

                        matchCount++;

                        String rollNumber = "";
                        String name = "";
                        StringBuilder matchingFields = new StringBuilder();
                        int matchingFieldCount = 0;

                        // Read the record again to extract details
                        try (BufferedReader displayReader =
                                     new BufferedReader(new FileReader(file))) {

                            while ((line = displayReader.readLine()) != null) {

                                String lowerLine = line.toLowerCase();

                                // Store student Roll Number
                                if (line.startsWith("Roll Number")) {
                                    rollNumber = line;
                                }

                                // Store student Name
                                if (line.startsWith("Name")) {
                                    name = line;
                                }

                                // Check each field using KMP
                                if (KMP.search(lowerLine, searchPattern)) {

                                    // Don't include Roll Number and Name here
                                    // because they are shown in Student Identity
                                    if (!line.startsWith("Roll Number")
                                            && !line.startsWith("Name")) {

                                        matchingFields.append("> ")
                                                .append(line)
                                                .append("\n");

                                        matchingFieldCount++;
                                    }
                                }
                            }
                        }

                        System.out.println("\nMatching Student Record #"
                                + matchCount);

                        System.out.println("\n----------- STUDENT IDENTITY -----------");
                        System.out.println(rollNumber);
                        System.out.println(name);
                        System.out.println("----------------------------------------");

                        System.out.println("\nMATCHING INFORMATION");
                        System.out.println("----------------------------------------");

                        // Display fields containing the search pattern
                        if (matchingFields.length() > 0) {

                            System.out.print(matchingFields);

                        } else {

                            // If the search matches only Roll Number
                            if (KMP.search(
                                    rollNumber.toLowerCase(),
                                    searchPattern)) {

                                System.out.println("> " + rollNumber);
                                matchingFieldCount++;
                            }

                            // If the search matches only Name
                            if (KMP.search(
                                    name.toLowerCase(),
                                    searchPattern)) {

                                System.out.println("> " + name);
                                matchingFieldCount++;
                            }
                        }

                        System.out.println("\nMatch Summary: \""
                                + pattern + "\" found in "
                                + matchingFieldCount + " field(s)");

                        System.out.println("==============================================");
                    }

                } catch (IOException e) {

                    System.out.println(
                            "Error reading: " + file.getName()
                    );
                }
            }
        }

        System.out.println("\nTotal Matching Students: " + matchCount);
        System.out.println("==============================================");
    }
}

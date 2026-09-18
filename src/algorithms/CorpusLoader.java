package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorpusLoader {

    private static void collectTextFiles(File folder, List<File> textFiles) {

        File[] files = folder.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {

            if (file.isDirectory()) {

                collectTextFiles(file, textFiles);

            } else if (file.isFile()
                    && file.getName().toLowerCase().endsWith(".txt")) {

                textFiles.add(file);
            }
        }
    }


    // =========================================================
    // NORMAL SEARCH USING KMP
    // =========================================================

    public static void searchCorpus(String folderPath, String pattern) {

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {

            System.out.println("Corpus folder not found.");
            return;
        }

        List<File> files = new ArrayList<>();

        collectTextFiles(folder, files);

        if (files.isEmpty()) {

            System.out.println(
                    "No .txt files found in the corpus."
            );

            return;
        }

        int matchCount = 0;

        String searchPattern = pattern.toLowerCase();

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "         INTELLIGENT SEARCH RESULT"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Search Query: " + pattern
        );


        for (File file : files) {

            StringBuilder content = new StringBuilder();

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    content.append(line).append("\n");
                }


                // Search complete record using KMP
                boolean found = KMP.search(
                        content.toString().toLowerCase(),
                        searchPattern
                );


                if (found) {

                    matchCount++;

                    String rollNumber = "";
                    String name = "";

                    StringBuilder matchingFields =
                            new StringBuilder();

                    int matchingFieldCount = 0;


                    // Read record again to extract details
                    try (BufferedReader displayReader =
                                 new BufferedReader(
                                         new FileReader(file))) {

                        while ((line =
                                displayReader.readLine())
                                != null) {

                            String lowerLine =
                                    line.toLowerCase();


                            // Store Roll Number
                            if (line.startsWith("Roll Number")) {

                                rollNumber = line;
                            }


                            // Store Name
                            if (line.startsWith("Name")) {

                                name = line;
                            }


                            // Check each field using KMP
                            if (KMP.search(
                                    lowerLine,
                                    searchPattern)) {

                                if (!line.startsWith("Roll Number")
                                        && !line.startsWith("Name")) {

                                    matchingFields
                                            .append("> ")
                                            .append(line)
                                            .append("\n");

                                    matchingFieldCount++;
                                }
                            }
                        }
                    }


                    System.out.println(
                            "\nMatching Student Record #"
                                    + matchCount
                    );


                    System.out.println(
                            "\n----------- STUDENT IDENTITY -----------"
                    );

                    System.out.println(rollNumber);

                    System.out.println(name);

                    System.out.println(
                            "----------------------------------------"
                    );


                    System.out.println(
                            "\nMATCHING INFORMATION"
                    );

                    System.out.println(
                            "----------------------------------------"
                    );


                    if (matchingFields.length() > 0) {

                        System.out.print(matchingFields);

                    } else {

                        // Search Roll Number
                        if (KMP.search(
                                rollNumber.toLowerCase(),
                                searchPattern)) {

                            System.out.println(
                                    "> " + rollNumber
                            );

                            matchingFieldCount++;
                        }


                        // Search Name
                        if (KMP.search(
                                name.toLowerCase(),
                                searchPattern)) {

                            System.out.println(
                                    "> " + name
                            );

                            matchingFieldCount++;
                        }
                    }


                    System.out.println(
                            "\nMatch Summary: \""
                                    + pattern
                                    + "\" found in "
                                    + matchingFieldCount
                                    + " field(s)"
                    );


                    System.out.println(
                            "=============================================="
                    );
                }

            } catch (IOException e) {

                System.out.println(
                        "Error reading: "
                                + file.getName()
                );
            }
        }


        System.out.println(
                "\nTotal Matching Students: "
                        + matchCount
        );

        System.out.println(
                "=============================================="
        );


        // Save normal search
        SearchHistory.saveSearch(
                pattern,
                matchCount
        );
    }



    // =========================================================
    // FUZZY SEARCH USING EDIT DISTANCE
    // =========================================================

    public static void fuzzySearchCorpus(
            String folderPath,
            String pattern) {

        File folder = new File(folderPath);


        if (!folder.exists() || !folder.isDirectory()) {

            System.out.println(
                    "Corpus folder not found."
            );

            return;
        }


        List<File> files = new ArrayList<>();

        collectTextFiles(folder, files);


        if (files.isEmpty()) {

            System.out.println(
                    "No .txt files found in the corpus."
            );

            return;
        }


        String searchPattern =
                pattern.toLowerCase().trim();


        int matchCount = 0;


        // Maximum allowed Edit Distance
        int threshold = 2;


        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "       FUZZY SEARCH USING EDIT DISTANCE"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Search Query: " + pattern
        );

        System.out.println(
                "Maximum Edit Distance: " + threshold
        );


        // =====================================================
        // CHECK EVERY STUDENT RECORD
        // =====================================================

        for (File file : files) {

            StringBuilder content =
                    new StringBuilder();


            try (BufferedReader reader =
                         new BufferedReader(
                                 new FileReader(file))) {

                String line;


                // Read complete student record
                while ((line = reader.readLine()) != null) {

                    content.append(line).append("\n");
                }


                String lowerContent =
                        content.toString().toLowerCase();


                /*
                 * Split complete student record into words.
                 *
                 * Example:
                 *
                 * "Skills: Java Python SQL"
                 *
                 * becomes:
                 *
                 * Skills
                 * Java
                 * Python
                 * SQL
                 */

                String[] words =
                        lowerContent.split(
                                "[^a-zA-Z0-9]+"
                        );


                String closestWord = "";

                int smallestDistance =
                        Integer.MAX_VALUE;


                // =================================================
                // CALCULATE EDIT DISTANCE
                // =================================================

                for (String word : words) {

                    if (word.isEmpty()) {

                        continue;
                    }


                    int distance =
                            EditDistance.calculate(
                                    searchPattern,
                                    word
                            );


                    if (distance < smallestDistance) {

                        smallestDistance = distance;

                        closestWord = word;
                    }
                }


                // =================================================
                // CHECK FUZZY MATCH
                // =================================================

                if (smallestDistance <= threshold) {

                    matchCount++;


                    String rollNumber = "";

                    String name = "";


                    // Read record again to get student details
                    try (BufferedReader displayReader =
                                 new BufferedReader(
                                         new FileReader(file))) {

                        while ((line =
                                displayReader.readLine())
                                != null) {


                            if (line.startsWith(
                                    "Roll Number")) {

                                rollNumber = line;
                            }


                            if (line.startsWith("Name")) {

                                name = line;
                            }
                        }
                    }


                    // =================================================
                    // DISPLAY RESULT
                    // =================================================

                    System.out.println(
                            "\nMatching Student Record #"
                                    + matchCount
                    );


                    System.out.println(
                            "\n----------- STUDENT IDENTITY -----------"
                    );

                    System.out.println(rollNumber);

                    System.out.println(name);

                    System.out.println(
                            "----------------------------------------"
                    );


                    System.out.println(
                            "\nFUZZY MATCH INFORMATION"
                    );

                    System.out.println(
                            "----------------------------------------"
                    );


                    System.out.println(
                            "Query         : "
                                    + pattern
                    );


                    System.out.println(
                            "Closest Match : "
                                    + closestWord
                    );


                    System.out.println(
                            "Edit Distance : "
                                    + smallestDistance
                    );


                    System.out.println(
                            "----------------------------------------"
                    );
                }


            } catch (IOException e) {

                System.out.println(
                        "Error reading: "
                                + file.getName()
                );
            }
        }


        // =========================================================
        // FINAL RESULT
        // =========================================================

        System.out.println(
                "\nTotal Fuzzy Matching Students: "
                        + matchCount
        );

        System.out.println(
                "=============================================="
        );


        // Save fuzzy search
        SearchHistory.saveSearch(
                pattern,
                matchCount
        );
    }
}

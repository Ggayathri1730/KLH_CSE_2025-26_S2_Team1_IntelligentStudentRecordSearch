
package algorithms;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String corpusPath = "corpus/student-records";

        int choice;

        do {
            System.out.println(
                    "\n======================================");
            System.out.println(
                    " INTELLIGENT STUDENT RECORD SEARCH");
            System.out.println(
                    "======================================");

            System.out.println("1. Search Student Records (KMP)");
            System.out.println("2. Fuzzy Search using Edit Distance");
            System.out.println("3. View Search History");
            System.out.println("4. Clear Search History");
            System.out.println("5. Search using Naive Pattern Matching");
            System.out.println("6. Run Algorithm Benchmark");
            System.out.println("7. Exit");

            System.out.println(
                    "======================================");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                if (!scanner.hasNext()) {
                    System.out.println("\nInput ended. Exiting.");
                    scanner.close();
                    return;
                }

                System.out.println(
                        "Please enter a number between 1 and 7.");
                scanner.next();

                System.out.print("Enter your choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("\nEnter search pattern: ");
                    String pattern = scanner.nextLine();

                    if (pattern.trim().isEmpty()) {
                        System.out.println(
                                "Search pattern cannot be empty.");
                    } else {
                        CorpusLoader.searchCorpus(
                                corpusPath, pattern);
                    }
                    break;

                case 2:
                    System.out.print(
                            "\nEnter search pattern for fuzzy search: ");
                    String fuzzyPattern = scanner.nextLine();

                    if (fuzzyPattern.trim().isEmpty()) {
                        System.out.println(
                                "Search pattern cannot be empty.");
                    } else {
                        CorpusLoader.fuzzySearchCorpus(
                                corpusPath, fuzzyPattern);
                    }
                    break;

                case 3:
                    SearchHistory.viewHistory();
                    break;

                case 4:
                    SearchHistory.clearHistory();
                    break;

                case 5:
                    System.out.print(
                            "\nEnter pattern for Naive Pattern Matching: ");
                    String naivePattern = scanner.nextLine();

                    if (naivePattern.trim().isEmpty()) {
                        System.out.println(
                                "Search pattern cannot be empty.");
                    } else {
                        CorpusLoader.naiveSearchCorpus(
                                corpusPath, naivePattern);
                    }
                    break;

                case 6:
                    try {
                        AlgorithmBenchmark.main(new String[0]);
                    } catch (IOException e) {
                        System.out.println(
                                "Benchmark error: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println(
                            "\nThank you for using "
                            + "Intelligent Student Record Search!");
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please select 1 to 7.");
            }

        } while (choice != 7);

        scanner.close();
    }
}

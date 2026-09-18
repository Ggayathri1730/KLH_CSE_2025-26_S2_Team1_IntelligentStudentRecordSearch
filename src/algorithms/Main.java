package algorithms;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String corpusPath = "corpus/student-records";

        int choice;

        do {

            System.out.println(
                    "\n======================================"
            );

            System.out.println(
                    " INTELLIGENT STUDENT RECORD SEARCH"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "1. Search Student Records"
            );

            System.out.println(
                    "2. Fuzzy Search using Edit Distance"
            );

            System.out.println(
                    "3. View Search History"
            );

            System.out.println(
                    "4. Clear Search History"
            );

            System.out.println(
                    "5. Exit"
            );

            System.out.println(
                    "======================================"
            );

            System.out.print(
                    "Enter your choice: "
            );

            while (!scanner.hasNextInt()) {

                System.out.println(
                        "Please enter a number between 1 and 5."
                );

                scanner.next();

                System.out.print(
                        "Enter your choice: "
                );
            }

            choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                // =================================================
                // OPTION 1: NORMAL SEARCH
                // =================================================

                case 1:

                    System.out.print(
                            "\nEnter search pattern: "
                    );

                    String pattern =
                            scanner.nextLine();

                    if (pattern.trim().isEmpty()) {

                        System.out.println(
                                "Search pattern cannot be empty."
                        );

                    } else {

                        CorpusLoader.searchCorpus(
                                corpusPath,
                                pattern
                        );
                    }

                    break;


                // =================================================
                // OPTION 2: FUZZY SEARCH
                // =================================================

                case 2:

                    System.out.print(
                            "\nEnter search pattern for fuzzy search: "
                    );

                    String fuzzyPattern =
                            scanner.nextLine();

                    if (fuzzyPattern.trim().isEmpty()) {

                        System.out.println(
                                "Search pattern cannot be empty."
                        );

                    } else {

                        CorpusLoader.fuzzySearchCorpus(
                                corpusPath,
                                fuzzyPattern
                        );
                    }

                    break;


                // =================================================
                // OPTION 3: SEARCH HISTORY
                // =================================================

                case 3:

                    SearchHistory.viewHistory();

                    break;


                // =================================================
                // OPTION 4: CLEAR SEARCH HISTORY
                // =================================================

                case 4:

                    SearchHistory.clearHistory();

                    break;


                // =================================================
                // OPTION 5: EXIT
                // =================================================

                case 5:

                    System.out.println(
                            "\nThank you for using "
                            + "Intelligent Student Record Search!"
                    );

                    break;


                default:

                    System.out.println(
                            "\nInvalid choice. "
                            + "Please select 1 to 5."
                    );
            }

        } while (choice != 5);

        scanner.close();
    }
}

package algorithms;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String corpusPath = "corpus/student-records";
        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println(" INTELLIGENT STUDENT RECORD SEARCH");
            System.out.println("======================================");
            System.out.println("1. Search Student Records");
            System.out.println("2. View Search History");
            System.out.println("3. Clear Search History");
            System.out.println("4. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number between 1 and 4.");
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
                        System.out.println("Search pattern cannot be empty.");
                    } else {
                        CorpusLoader.searchCorpus(corpusPath, pattern);
                    }
                    break;

                case 2:
                    SearchHistory.viewHistory();
                    break;

                case 3:
                    SearchHistory.clearHistory();
                    break;

                case 4:
                    System.out.println("\nThank you for using Intelligent Student Record Search!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please select 1 to 4.");
            }

        } while (choice != 4);

        scanner.close();
    }
}

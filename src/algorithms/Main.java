package algorithms;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" Intelligent Student Record Search");
        System.out.println("======================================");

        System.out.print("Enter search pattern: ");
        String pattern = scanner.nextLine();

        String corpusPath = "corpus/student-records";

        CorpusLoader.searchCorpus(corpusPath, pattern);

        scanner.close();
    }
}

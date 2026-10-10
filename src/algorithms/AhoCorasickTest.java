package algorithms;

public class AhoCorasickTest {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("        AHO-CORASICK TEST");
        System.out.println("======================================");

        String[] patterns = {
            "java",
            "python",
            "sql",
            "machine learning"
        };

        String text =
                "Skills: Java Python SQL. " +
                "Experience in Machine Learning.";

        System.out.println("Text:");
        System.out.println(text);

        System.out.println("\nPatterns:");

        int totalLength = 0;

        for (int i = 0; i < patterns.length; i++) {

            System.out.println("- " + patterns[i]);

            totalLength += patterns[i].length();
        }

        AhoCorasick aho =
                new AhoCorasick(totalLength);

        for (int i = 0; i < patterns.length; i++) {

            aho.addPattern(patterns[i]);
        }

        aho.buildFailureLinks();

        int matches =
                aho.search(text.toLowerCase());

        System.out.println(
                "\nTotal Pattern Matches: " + matches
        );

        System.out.println("\nExpected:");
        System.out.println(
                "Java, Python, SQL and Machine Learning"
        );
        System.out.println(
                "should all be detected."
        );

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "          TEST COMPLETED"
        );

        System.out.println(
                "======================================"
        );
    }
}

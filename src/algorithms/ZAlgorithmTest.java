package algorithms;

public class ZAlgorithmTest {
    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("          Z-ALGORITHM TEST");
        System.out.println("======================================");

        String text = "abcababcab";
        String pattern = "abc";

        System.out.println("Text    : " + text);
        System.out.println("Pattern : " + pattern);

        boolean found = ZAlgorithm.search(text, pattern);

        System.out.println("Pattern Found: " + found);


        System.out.println("\nTesting pattern that does not exist:");

        String pattern2 = "xyz";

        System.out.println("Text    : " + text);
        System.out.println("Pattern : " + pattern2);

        boolean found2 = ZAlgorithm.search(text, pattern2);

        System.out.println("Pattern Found: " + found2);


        System.out.println("\nTesting student skill:");

        String studentText = "Skills: Java Python SQL";
        String skill = "Python";

        boolean found3 = ZAlgorithm.search(
                studentText.toLowerCase(),
                skill.toLowerCase()
        );

        System.out.println("Student Record: " + studentText);
        System.out.println("Search Skill: " + skill);
        System.out.println("Pattern Found: " + found3);


        System.out.println("\n======================================");
        System.out.println("          TEST COMPLETED");
        System.out.println("======================================");
    }
}

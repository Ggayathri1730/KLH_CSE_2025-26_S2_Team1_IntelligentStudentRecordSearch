package algorithms;

public class KMPTest {

    public static void main(String[] args) {

        String text = "Java Python Data Structures and Algorithms";
        String pattern = "Python";

        boolean result = KMP.search(text, pattern);

        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("KMP Match: " + result);
    }
}

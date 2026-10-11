
package algorithms;

public class NaivePatternMatching {

    /**
     * Searches for the first occurrence of a pattern in a text
     * using the Naive Pattern Matching algorithm.
     *
     * @param text the text to search
     * @param pattern the pattern to find
     * @return the starting index if found, otherwise -1
     */
    public static int search(String text, String pattern) {

        if (text == null || pattern == null) {
            return -1;
        }

        int n = text.length();
        int m = pattern.length();

        // An empty pattern is considered found at index 0
        if (m == 0) {
            return 0;
        }

        // Pattern cannot match if it is longer than the text
        if (m > n) {
            return -1;
        }

        // Check every possible starting position
        for (int i = 0; i <= n - m; i++) {

            int j = 0;

            // Compare characters one by one
            while (j < m &&
                   text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            // All characters matched
            if (j == m) {
                return i;
            }
        }

        // Pattern was not found
        return -1;
    }

    public static void main(String[] args) {

        String text = "intelligent student record search";
        String pattern = "student";

        int result = search(text, pattern);

        if (result != -1) {
            System.out.println("Pattern found at index: " + result);
        } else {
            System.out.println("Pattern not found.");
        }
    }
}

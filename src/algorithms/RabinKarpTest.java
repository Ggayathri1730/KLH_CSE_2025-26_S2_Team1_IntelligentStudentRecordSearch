package algorithms;

public class RabinKarpTest {

    public static void main(String[] args) {
        assertResult("Java Python Data Structures and Algorithms", "Python", true);
        assertResult("Java Python Data Structures and Algorithms", "Ruby", false);
        assertResult("abc", "", true);
        assertResult("ab", "abc", false);
        assertResult("Straße", "ße", true);

        System.out.println("Rabin-Karp Test: PASSED");
    }

    private static void assertResult(String text, String pattern, boolean expected) {
        boolean actual = RabinKarp.search(text, pattern);
        if (actual != expected) {
            throw new AssertionError(
                    "Rabin-Karp search for \"" + pattern + "\" in \"" + text
                            + "\" expected " + expected + " but got " + actual);
        }
    }
}

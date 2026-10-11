package algorithms;

public class KMPTest {

    public static void main(String[] args) {
        assertResult(
                "Java Python Data Structures and Algorithms",
                "Python",
                true);
        assertResult("abcdef", "xyz", false);
        assertResult("hello", "", true);
        assertResult("ab", "abc", false);
        assertResult("ABABDABACDABABCABAB", "ABABCABAB", true);
        assertResult("aaaaab", "aaab", true);
        assertResult("", "a", false);
        assertResult("", "", true);

        System.out.println("KMP Test: PASSED");
    }

    private static void assertResult(
            String text, String pattern, boolean expected) {
        boolean actual = KMP.search(text, pattern);
        if (actual != expected) {
            throw new AssertionError(
                    "KMP search for \"" + pattern + "\" in \"" + text
                            + "\" expected " + expected + " but got " + actual);
        }
    }
}

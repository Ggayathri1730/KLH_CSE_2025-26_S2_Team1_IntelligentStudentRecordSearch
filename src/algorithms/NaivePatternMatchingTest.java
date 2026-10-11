package algorithms;

public class NaivePatternMatchingTest {

    public static void main(String[] args) {
        test("hello world", "world", 6);
        test("banana", "ana", 1);
        test("abcdef", "xyz", -1);
        test("java", "javascript", -1);
        test("hello", "", 0);
        test("", "a", -1);
        test("hello", "hello", 0);
        test(null, "hello", -1);
        test("hello", null, -1);

        System.out.println("All Naive Pattern Matching tests passed!");
    }

    private static void test(String text, String pattern, int expected) {
        int actual = NaivePatternMatching.search(text, pattern);

        if (actual != expected) {
            throw new AssertionError(
                "Test failed: expected " + expected
                + " but got " + actual
            );
        }
    }
}

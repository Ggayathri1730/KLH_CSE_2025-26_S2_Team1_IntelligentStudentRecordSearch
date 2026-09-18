package algorithms;

public class SuffixArrayTest {

    public static void main(String[] args) {

        System.out.println(
                "======================================"
        );

        System.out.println(
                "          SUFFIX ARRAY TEST"
        );

        System.out.println(
                "======================================"
        );

        String text = "banana";

        System.out.println(
                "Text: " + text
        );

        SuffixArray suffixArray =
                new SuffixArray(text);

        suffixArray.printSuffixes();

        System.out.println(
                "\nExpected suffix order:"
        );

        System.out.println(
                "5 -> a"
        );

        System.out.println(
                "3 -> ana"
        );

        System.out.println(
                "1 -> anana"
        );

        System.out.println(
                "0 -> banana"
        );

        System.out.println(
                "4 -> na"
        );

        System.out.println(
                "2 -> nana"
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

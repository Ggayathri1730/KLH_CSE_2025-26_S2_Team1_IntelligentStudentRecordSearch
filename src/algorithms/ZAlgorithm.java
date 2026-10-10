package algorithms;

public class ZAlgorithm {

    // Returns true if pattern is found in text
    public static boolean search(String text, String pattern) {

        if (pattern == null || text == null) {
            return false;
        }

        if (pattern.length() == 0) {
            return true;
        }

        String combined = pattern + "$" + text;

        int[] z = new int[combined.length()];

        int left = 0;
        int right = 0;

        for (int i = 1; i < combined.length(); i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < combined.length()
                    && combined.charAt(z[i]) == combined.charAt(i + z[i])) {

                z[i]++;
            }

            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }

            if (z[i] == pattern.length()) {
                return true;
            }
        }

        return false;
    }
}

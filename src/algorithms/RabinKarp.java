package algorithms;

public class RabinKarp {

    private static final long BASE = 256;
    private static final long MODULUS = 1_000_000_007;

    public static boolean search(String text, String pattern) {
        if (text == null || pattern == null) {
            return false;
        }
        if (pattern.isEmpty()) {
            return true;
        }
        if (pattern.length() > text.length()) {
            return false;
        }

        int patternLength = pattern.length();
        long highestPower = 1;
        for (int i = 1; i < patternLength; i++) {
            highestPower = (highestPower * BASE) % MODULUS;
        }

        long patternHash = 0;
        long windowHash = 0;
        for (int i = 0; i < patternLength; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MODULUS;
            windowHash = (windowHash * BASE + text.charAt(i)) % MODULUS;
        }

        for (int start = 0; start <= text.length() - patternLength; start++) {
            if (patternHash == windowHash
                    && text.regionMatches(start, pattern, 0, patternLength)) {
                return true;
            }

            if (start < text.length() - patternLength) {
                long leadingChar = (text.charAt(start) * highestPower) % MODULUS;
                windowHash = (windowHash - leadingChar + MODULUS) % MODULUS;
                windowHash = (windowHash * BASE + text.charAt(start + patternLength))
                        % MODULUS;
            }
        }

        return false;
    }
}

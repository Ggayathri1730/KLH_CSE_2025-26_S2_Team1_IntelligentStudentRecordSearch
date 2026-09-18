package algorithms;

public class EditDistance {
    /**
     * Calculates the Levenshtein Edit Distance between two strings.
     *
     * Operations allowed:
     * 1. Insert a character
     * 2. Delete a character
     * 3. Replace a character
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(n * m)
     */
    public static int calculate(String str1, String str2) {

        if (str1 == null || str2 == null) {
            return -1;
        }

        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Convert empty string to str2
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        // Convert empty string to str1
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {

                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + min(insert, delete, replace);
                }
            }
        }

        return dp[n][m];
    }

    private static int min(int a, int b, int c) {

        int result = a;

        if (b < result) {
            result = b;
        }

        if (c < result) {
            result = c;
        }

        return result;
    }
}

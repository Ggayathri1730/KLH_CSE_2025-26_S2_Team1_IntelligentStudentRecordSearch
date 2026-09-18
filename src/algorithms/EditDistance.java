package algorithms;

public class EditDistance {

    // Calculates the Levenshtein Edit Distance between two strings
    public static int calculate(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();

        // DP table
        int[][] dp = new int[m + 1][n + 1];

        // Converting empty string to str2
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Converting str1 to empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                // Characters are the same
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    // Insert a character
                    int insert = dp[i][j - 1];

                    // Delete a character
                    int delete = dp[i - 1][j];

                    // Replace a character
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(
                            insert,
                            Math.min(delete, replace)
                    );
                }
            }
        }

        return dp[m][n];
    }
}

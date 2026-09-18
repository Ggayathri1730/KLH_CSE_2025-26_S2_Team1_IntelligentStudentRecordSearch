package algorithms;

public class SuffixArray {

    private String text;
    private int[] suffixArray;

    public SuffixArray(String text) {

        this.text = text;

        if (text == null) {
            suffixArray = new int[0];
        } else {
            suffixArray = buildSuffixArray(text);
        }
    }

    /*
     * Builds the suffix array using the doubling technique.
     *
     * Time Complexity: O(n log^2 n)
     * Space Complexity: O(n)
     */
    private int[] buildSuffixArray(String s) {

        int n = s.length();

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] newRank = new int[n];

        // Initial suffix ranks based on characters
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = s.charAt(i);
        }

        // Double the comparison length each round
        for (int length = 1; length < n; length *= 2) {

            mergeSort(sa, rank, length, 0, n - 1);

            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {

                int previous = sa[i - 1];
                int current = sa[i];

                if (compareSuffixes(
                        previous,
                        current,
                        rank,
                        length,
                        n)) {

                    newRank[current] =
                            newRank[previous] + 1;

                } else {

                    newRank[current] =
                            newRank[previous];
                }
            }

            for (int i = 0; i < n; i++) {
                rank[i] = newRank[i];
            }

            // All suffixes have different ranks
            if (rank[sa[n - 1]] == n - 1) {
                break;
            }

            // Prevent integer overflow
            if (length > n / 2) {
                break;
            }
        }

        return sa;
    }

    /*
     * Returns true if suffix at index i should come
     * before suffix at index j.
     */
    private boolean compareSuffixes(
            int i,
            int j,
            int[] rank,
            int length,
            int n) {

        if (rank[i] != rank[j]) {
            return rank[i] < rank[j];
        }

        int rankI =
                (i + length < n)
                        ? rank[i + length]
                        : -1;

        int rankJ =
                (j + length < n)
                        ? rank[j + length]
                        : -1;

        return rankI < rankJ;
    }

    /*
     * Merge sort for suffix indices.
     * This avoids using Java's built-in sorting.
     */
    private void mergeSort(
            int[] sa,
            int[] rank,
            int length,
            int left,
            int right) {

        if (left >= right) {
            return;
        }

        int middle =
                left + (right - left) / 2;

        mergeSort(
                sa,
                rank,
                length,
                left,
                middle
        );

        mergeSort(
                sa,
                rank,
                length,
                middle + 1,
                right
        );

        merge(
                sa,
                rank,
                length,
                left,
                middle,
                right
        );
    }

    private void merge(
            int[] sa,
            int[] rank,
            int length,
            int left,
            int middle,
            int right) {

        int[] temp =
                new int[right - left + 1];

        int i = left;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= right) {

            if (compareSuffixes(
                    sa[i],
                    sa[j],
                    rank,
                    length,
                    text.length())) {

                temp[k++] = sa[i++];

            } else {

                temp[k++] = sa[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = sa[i++];
        }

        while (j <= right) {
            temp[k++] = sa[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            sa[left + x] = temp[x];
        }
    }

    /*
     * Returns the constructed suffix array.
     */
    public int[] getSuffixArray() {

        return suffixArray;
    }

    /*
     * Prints every suffix in suffix-array order.
     */
    public void printSuffixes() {

        System.out.println(
                "\nSuffix Array:"
        );

        for (int i = 0; i < suffixArray.length; i++) {

            int index = suffixArray[i];

            System.out.println(
                    index + " -> " + text.substring(index)
            );
        }
    }
}

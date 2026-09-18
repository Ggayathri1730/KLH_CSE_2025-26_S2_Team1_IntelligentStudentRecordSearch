package algorithms;

public class AhoCorasick {
     private static final int ALPHABET_SIZE = 128;

    private int[][] next;
    private int[] failure;
    private boolean[] output;
    private int nodeCount;

    public AhoCorasick(int maxPatternsLength) {

        int maxNodes = maxPatternsLength + 1;

        next = new int[maxNodes][ALPHABET_SIZE];
        failure = new int[maxNodes];
        output = new boolean[maxNodes];

        nodeCount = 1;
    }

    // Add one pattern to the trie
    public void addPattern(String pattern) {

        if (pattern == null || pattern.length() == 0) {
            return;
        }

        int current = 0;

        for (int i = 0; i < pattern.length(); i++) {

            char ch = pattern.charAt(i);

            if (ch >= ALPHABET_SIZE) {
                continue;
            }

            if (next[current][ch] == 0) {

                next[current][ch] = nodeCount;
                nodeCount++;
            }

            current = next[current][ch];
        }

        output[current] = true;
    }

    // Build failure links using BFS
    public void buildFailureLinks() {

        int[] queue = new int[nodeCount];

        int front = 0;
        int rear = 0;

        // Initialize root's children
        for (int ch = 0; ch < ALPHABET_SIZE; ch++) {

            int child = next[0][ch];

            if (child != 0) {

                failure[child] = 0;
                queue[rear++] = child;
            }
        }

        // BFS
        while (front < rear) {

            int current = queue[front++];

            for (int ch = 0; ch < ALPHABET_SIZE; ch++) {

                int child = next[current][ch];

                if (child != 0) {

                    int fallback = failure[current];

                    while (fallback != 0
                            && next[fallback][ch] == 0) {

                        fallback = failure[fallback];
                    }

                    if (next[fallback][ch] != 0) {
                        failure[child] =
                                next[fallback][ch];
                    } else {
                        failure[child] = 0;
                    }

                    if (output[failure[child]]) {
                        output[child] = true;
                    }

                    queue[rear++] = child;
                }
            }
        }
    }

    // Search for all added patterns
    public int search(String text) {

        if (text == null) {
            return 0;
        }

        int current = 0;
        int matchCount = 0;

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= ALPHABET_SIZE) {
                current = 0;
                continue;
            }

            while (current != 0
                    && next[current][ch] == 0) {

                current = failure[current];
            }

            if (next[current][ch] != 0) {
                current = next[current][ch];
            } else {
                current = 0;
            }

            if (output[current]) {
                matchCount++;
            }
        }

        return matchCount;
    }
}

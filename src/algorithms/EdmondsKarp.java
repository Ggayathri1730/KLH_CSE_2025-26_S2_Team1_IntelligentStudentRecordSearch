package algorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class EdmondsKarp {

    public static int maxFlow(int[][] capacity, int source, int sink) {
        validateGraph(capacity, source, sink);

        int n = capacity.length;
        int[][] residual = copyMatrix(capacity);
        int[] parent = new int[n];
        int maxFlow = 0;

        while (bfs(residual, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private static boolean bfs(int[][] residual, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[residual.length];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < residual.length; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;

                    if (v == sink) {
                        return true;
                    }

                    queue.offer(v);
                }
            }
        }

        return false;
    }

    private static int[][] copyMatrix(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }

    private static void validateGraph(int[][] capacity, int source, int sink) {
        if (capacity == null || capacity.length == 0) {
            throw new IllegalArgumentException("Capacity graph must not be empty.");
        }

        int n = capacity.length;
        if (source < 0 || source >= n || sink < 0 || sink >= n) {
            throw new IllegalArgumentException("Source and sink must be valid vertices.");
        }
        if (source == sink) {
            throw new IllegalArgumentException("Source and sink must be different vertices.");
        }

        for (int i = 0; i < n; i++) {
            if (capacity[i] == null || capacity[i].length != n) {
                throw new IllegalArgumentException("Capacity graph must be a square matrix.");
            }
            for (int value : capacity[i]) {
                if (value < 0) {
                    throw new IllegalArgumentException("Capacities cannot be negative.");
                }
            }
        }
    }
}

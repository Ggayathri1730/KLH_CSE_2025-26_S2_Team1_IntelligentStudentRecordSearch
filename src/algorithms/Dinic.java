package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Dinic {

    private static class Edge {
        int to;
        int reverseIndex;
        int capacity;

        Edge(int to, int reverseIndex, int capacity) {
            this.to = to;
            this.reverseIndex = reverseIndex;
            this.capacity = capacity;
        }
    }

    private final List<List<Edge>> graph;
    private final int[] level;
    private final int[] nextEdge;

    public Dinic(int vertices) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }

        graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        level = new int[vertices];
        nextEdge = new int[vertices];
    }

    public void addEdge(int from, int to, int capacity) {
        validateVertex(from);
        validateVertex(to);

        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }

        Edge forward = new Edge(to, graph.get(to).size(), capacity);
        Edge backward = new Edge(from, graph.get(from).size(), 0);

        graph.get(from).add(forward);
        graph.get(to).add(backward);
    }

    public int maxFlow(int source, int sink) {
        validateVertex(source);
        validateVertex(sink);

        if (source == sink) {
            throw new IllegalArgumentException("Source and sink must be different vertices.");
        }

        int totalFlow = 0;

        while (buildLevelGraph(source, sink)) {
            Arrays.fill(nextEdge, 0);

            int flow;
            while ((flow = sendFlow(source, sink, Integer.MAX_VALUE)) > 0) {
                totalFlow += flow;
            }
        }

        return totalFlow;
    }

    public static int maxFlow(int[][] capacity, int source, int sink) {
        validateMatrix(capacity, source, sink);

        Dinic dinic = new Dinic(capacity.length);
        for (int u = 0; u < capacity.length; u++) {
            for (int v = 0; v < capacity.length; v++) {
                if (capacity[u][v] > 0) {
                    dinic.addEdge(u, v, capacity[u][v]);
                }
            }
        }

        return dinic.maxFlow(source, sink);
    }

    private boolean buildLevelGraph(int source, int sink) {
        Arrays.fill(level, -1);
        Queue<Integer> queue = new ArrayDeque<>();

        level[source] = 0;
        queue.offer(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (Edge edge : graph.get(u)) {
                if (edge.capacity > 0 && level[edge.to] == -1) {
                    level[edge.to] = level[u] + 1;
                    queue.offer(edge.to);
                }
            }
        }

        return level[sink] != -1;
    }

    private int sendFlow(int u, int sink, int flow) {
        if (u == sink) {
            return flow;
        }

        List<Edge> edges = graph.get(u);

        while (nextEdge[u] < edges.size()) {
            Edge edge = edges.get(nextEdge[u]);

            if (edge.capacity > 0 && level[edge.to] == level[u] + 1) {
                int currentFlow = Math.min(flow, edge.capacity);
                int pushedFlow = sendFlow(edge.to, sink, currentFlow);

                if (pushedFlow > 0) {
                    edge.capacity -= pushedFlow;
                    Edge reverse = graph.get(edge.to).get(edge.reverseIndex);
                    reverse.capacity += pushedFlow;
                    return pushedFlow;
                }
            }

            nextEdge[u]++;
        }

        return 0;
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= graph.size()) {
            throw new IllegalArgumentException("Invalid vertex: " + vertex);
        }
    }

    private static void validateMatrix(int[][] capacity, int source, int sink) {
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

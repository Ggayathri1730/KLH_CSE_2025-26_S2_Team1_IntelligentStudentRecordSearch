package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BipartiteMatching {

    public static class Result {
        private final int maximumMatches;
        private final Map<String, String> matches;
        private final List<String> unmatchedStudents;

        Result(int maximumMatches,
               Map<String, String> matches,
               List<String> unmatchedStudents) {
            this.maximumMatches = maximumMatches;
            this.matches = new LinkedHashMap<>(matches);
            this.unmatchedStudents = new ArrayList<>(unmatchedStudents);
        }

        public int getMaximumMatches() {
            return maximumMatches;
        }

        public Map<String, String> getMatches() {
            return new LinkedHashMap<>(matches);
        }

        public List<String> getUnmatchedStudents() {
            return new ArrayList<>(unmatchedStudents);
        }
    }

    public static Result matchStudentsToProjects(
            String[] students,
            String[] projects,
            boolean[][] eligibility) {

        validateInput(students, projects, eligibility);

        int studentCount = students.length;
        int projectCount = projects.length;

        int source = 0;
        int firstStudent = 1;
        int firstProject = firstStudent + studentCount;
        int sink = firstProject + projectCount;
        int vertices = sink + 1;

        int[][] capacity = new int[vertices][vertices];

        for (int i = 0; i < studentCount; i++) {
            capacity[source][firstStudent + i] = 1;
        }

        for (int i = 0; i < studentCount; i++) {
            for (int j = 0; j < projectCount; j++) {
                if (eligibility[i][j]) {
                    capacity[firstStudent + i][firstProject + j] = 1;
                }
            }
        }

        for (int j = 0; j < projectCount; j++) {
            capacity[firstProject + j][sink] = 1;
        }

        int[][] residual = copyMatrix(capacity);
        int[] parent = new int[vertices];
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

        Map<String, String> matches = new LinkedHashMap<>();
        List<String> unmatchedStudents = new ArrayList<>();

        for (int i = 0; i < studentCount; i++) {
            int studentVertex = firstStudent + i;
            boolean matched = false;

            for (int j = 0; j < projectCount; j++) {
                int projectVertex = firstProject + j;

                if (eligibility[i][j]
                        && capacity[studentVertex][projectVertex] == 1
                        && residual[studentVertex][projectVertex] == 0) {
                    matches.put(students[i], projects[j]);
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                unmatchedStudents.add(students[i]);
            }
        }

        return new Result(maxFlow, matches, unmatchedStudents);
    }

    public static int maximumMatching(boolean[][] eligibility) {
        if (eligibility == null) {
            throw new IllegalArgumentException("Eligibility matrix must not be null.");
        }

        int studentCount = eligibility.length;
        int projectCount = studentCount == 0 ? 0 : eligibility[0].length;

        String[] students = new String[studentCount];
        String[] projects = new String[projectCount];

        for (int i = 0; i < studentCount; i++) {
            students[i] = "Student " + (i + 1);
        }
        for (int j = 0; j < projectCount; j++) {
            projects[j] = "Project " + (j + 1);
        }

        return matchStudentsToProjects(students, projects, eligibility)
                .getMaximumMatches();
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
                    visited[v] = true;
                    parent[v] = u;

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

    private static void validateInput(
            String[] students,
            String[] projects,
            boolean[][] eligibility) {

        if (students == null || projects == null || eligibility == null) {
            throw new IllegalArgumentException("Students, projects, and eligibility cannot be null.");
        }

        if (eligibility.length != students.length) {
            throw new IllegalArgumentException(
                    "Eligibility rows must match the number of students.");
        }

        for (int i = 0; i < eligibility.length; i++) {
            if (eligibility[i] == null || eligibility[i].length != projects.length) {
                throw new IllegalArgumentException(
                        "Each eligibility row must match the number of projects.");
            }
        }
    }
}

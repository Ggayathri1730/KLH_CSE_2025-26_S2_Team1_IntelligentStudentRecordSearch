package algorithms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class AlgorithmBenchmark {

    private static final Path DEFAULT_CORPUS_PATH =
            Path.of("corpus", "student-records");

    // Updated queries selected from corpus query-count results.
    private static final List<String> QUERIES = List.of(
            "solidity",
            "docker",
            "blockchain",
            "image processing",
            "human computer interaction",
            "benchmark-no-match-934872"
    );

    private static final int WARMUP_RUNS = 3;
    private static final int MEASUREMENT_RUNS = 7;

    private static volatile long resultSink;

    private record StudentRecord(String relativePath, String content) {
    }

    @FunctionalInterface
    private interface Matcher {
        boolean matches(String text, String query);
    }

    public static void main(String[] args) throws IOException {

        if (args.length > 1) {
            throw new IllegalArgumentException(
                    "Usage: java algorithms.AlgorithmBenchmark [corpus-directory]");
        }

        Path corpusPath = args.length == 0
                ? DEFAULT_CORPUS_PATH
                : Path.of(args[0]);

        List<StudentRecord> records = loadRecords(corpusPath);

        // Define the three exact-pattern search algorithms.
        Matcher naive = (text, query) ->
                NaivePatternMatching.search(text, query) >= 0;

        Matcher kmp = KMP::search;
        Matcher rabinKarp = RabinKarp::search;

        // Collect matching records for each algorithm.
        List<List<String>> naiveResults =
                collectResults(records, naive);

        List<List<String>> kmpResults =
                collectResults(records, kmp);

        List<List<String>> rabinKarpResults =
                collectResults(records, rabinKarp);

        // Verify that all algorithms return the same matching records.
        verifyConsistentResults(
                naiveResults, kmpResults, rabinKarpResults);

        System.out.println("Corpus: "
                + corpusPath.toAbsolutePath().normalize());
        System.out.println("Student records: " + records.size());
        System.out.println("Queries: " + QUERIES.size());
        System.out.println("Result consistency: PASSED");

        for (int i = 0; i < QUERIES.size(); i++) {
            System.out.printf(
                    "  \"%s\": %d matching records%n",
                    QUERIES.get(i), naiveResults.get(i).size());
        }

        // Warm up all three algorithms.
        warmUp(records, naive, kmp, rabinKarp);

        long[] naiveTimes = new long[MEASUREMENT_RUNS];
        long[] kmpTimes = new long[MEASUREMENT_RUNS];
        long[] rabinKarpTimes = new long[MEASUREMENT_RUNS];

        // Alternate execution order to reduce ordering bias.
        for (int run = 0; run < MEASUREMENT_RUNS; run++) {

            if (run % 3 == 0) {
                naiveTimes[run] = measureBatch(records, naive);
                kmpTimes[run] = measureBatch(records, kmp);
                rabinKarpTimes[run] = measureBatch(records, rabinKarp);

            } else if (run % 3 == 1) {
                rabinKarpTimes[run] = measureBatch(records, rabinKarp);
                naiveTimes[run] = measureBatch(records, naive);
                kmpTimes[run] = measureBatch(records, kmp);

            } else {
                kmpTimes[run] = measureBatch(records, kmp);
                rabinKarpTimes[run] = measureBatch(records, rabinKarp);
                naiveTimes[run] = measureBatch(records, naive);
            }
        }

        System.out.println("Warm-up runs per algorithm: " + WARMUP_RUNS);
        System.out.println("Timed runs per algorithm: " + MEASUREMENT_RUNS);

        printMedian("Naive", naiveTimes);
        printMedian("KMP", kmpTimes);
        printMedian("Rabin-Karp", rabinKarpTimes);
    }

    private static List<StudentRecord> loadRecords(Path corpusPath)
            throws IOException {

        Path root = corpusPath.toAbsolutePath().normalize();

        if (!Files.isDirectory(root)) {
            throw new IOException("Corpus directory not found: " + root);
        }

        List<Path> files;

        try (var paths = Files.walk(root)) {
            files = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString()
                            .toLowerCase(Locale.ROOT).endsWith(".txt"))
                    .sorted(Comparator.comparing(
                            path -> root.relativize(path).toString()))
                    .toList();
        }

        if (files.isEmpty()) {
            throw new IOException(
                    "No .txt student records found in: " + root);
        }

        List<StudentRecord> records = new ArrayList<>(files.size());

        for (Path file : files) {
            String content =
                    Files.readString(file).toLowerCase(Locale.ROOT);

            records.add(new StudentRecord(
                    root.relativize(file).toString(), content));
        }

        return List.copyOf(records);
    }

    private static List<List<String>> collectResults(
            List<StudentRecord> records, Matcher matcher) {

        List<List<String>> results = new ArrayList<>(QUERIES.size());

        for (String query : QUERIES) {
            results.add(findMatches(records, query, matcher));
        }

        return results;
    }

    private static List<String> findMatches(
            List<StudentRecord> records,
            String query,
            Matcher matcher) {

        List<String> matches = new ArrayList<>();

        for (StudentRecord record : records) {
            if (matcher.matches(record.content(), query)) {
                matches.add(record.relativePath());
            }
        }

        return matches;
    }

    private static void verifyConsistentResults(
            List<List<String>> naiveResults,
            List<List<String>> kmpResults,
            List<List<String>> rabinKarpResults) {

        for (int i = 0; i < QUERIES.size(); i++) {

            if (!naiveResults.get(i).equals(kmpResults.get(i))
                    || !kmpResults.get(i).equals(rabinKarpResults.get(i))) {

                throw new IllegalStateException(
                        "Search results differ for query: "
                                + QUERIES.get(i)
                                + "\nNaive: " + naiveResults.get(i)
                                + "\nKMP: " + kmpResults.get(i)
                                + "\nRabin-Karp: "
                                + rabinKarpResults.get(i));
            }
        }
    }

    private static void warmUp(
            List<StudentRecord> records,
            Matcher naive,
            Matcher kmp,
            Matcher rabinKarp) {

        for (int run = 0; run < WARMUP_RUNS; run++) {

            if (run % 3 == 0) {
                measureBatch(records, naive);
                measureBatch(records, kmp);
                measureBatch(records, rabinKarp);

            } else if (run % 3 == 1) {
                measureBatch(records, rabinKarp);
                measureBatch(records, naive);
                measureBatch(records, kmp);

            } else {
                measureBatch(records, kmp);
                measureBatch(records, rabinKarp);
                measureBatch(records, naive);
            }
        }
    }

    private static long measureBatch(
            List<StudentRecord> records, Matcher matcher) {

        long start = System.nanoTime();
        long matchCount = 0;

        for (String query : QUERIES) {
            matchCount += findMatches(records, query, matcher).size();
        }

        long elapsed = System.nanoTime() - start;

        resultSink = matchCount;

        return elapsed;
    }

    private static void printMedian(String algorithm, long[] measurements) {

        long medianTime = median(measurements);

        System.out.printf(
                "%s median: %,d ns (%.3f ms)%n",
                algorithm,
                medianTime,
                medianTime / 1_000_000.0);
    }

    private static long median(long[] measurements) {

        long[] sorted = measurements.clone();
        java.util.Arrays.sort(sorted);

        return sorted[sorted.length / 2];
    }
}

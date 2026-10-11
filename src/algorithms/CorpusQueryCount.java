package algorithms;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class CorpusQueryCount {

    public static void main(String[] args) throws Exception {
        Path root = Path.of("corpus", "student-records");

        List<String> queries = List.of(
                "java",
                "python",
                "data structures",
                "machine learning"
        );

        int totalFiles = 0;
        long[] fullCounts = new long[queries.size()];
        long[] personalCounts = new long[queries.size()];

        try (Stream<Path> paths = Files.walk(root)) {
            List<Path> files = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase(Locale.ROOT)
                            .endsWith(".txt"))
                    .toList();

            totalFiles = files.size();

            for (Path file : files) {
                String text = Files.readString(file)
                        .toLowerCase(Locale.ROOT);

                int marker = text.indexOf("current project:");
                String personalSection = marker >= 0
                        ? text.substring(0, marker)
                        : text;

                for (int i = 0; i < queries.size(); i++) {
                    if (text.contains(queries.get(i))) {
                        fullCounts[i]++;
                    }
                    if (personalSection.contains(queries.get(i))) {
                        personalCounts[i]++;
                    }
                }
            }
        }

        System.out.println("Total files: " + totalFiles);

        for (int i = 0; i < queries.size(); i++) {
            System.out.println("\nQuery: " + queries.get(i));
            System.out.println("Full record matches: " + fullCounts[i]);
            System.out.println(
                    "Before Current Project matches: "
                            + personalCounts[i]);
        }
    }
}


package algorithms;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public class CorpusQueryDiscovery {

    public static void main(String[] args) throws Exception {
        Path root = Path.of("corpus", "student-records");

        List<String> queries = List.of(
                "java",
                "python",
                "solidity",
                "docker",
                "blockchain",
                "image processing",
                "human computer interaction",
                "competitive programming",
                "certificate verification",
                "artificial intelligence",
                "cyber security",
                "database systems",
                "project management",
                "warangal",
                "chennai"
        );

        Map<String, Integer> counts = new LinkedHashMap<>();
        for (String query : queries) {
            counts.put(query, 0);
        }

        try (Stream<Path> paths = Files.walk(root)) {
            List<Path> files = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase(Locale.ROOT)
                            .endsWith(".txt"))
                    .toList();

            for (Path file : files) {
                String text = Files.readString(file)
                        .toLowerCase(Locale.ROOT);

                for (String query : queries) {
                    if (text.contains(query)) {
                        counts.put(query, counts.get(query) + 1);
                    }
                }
            }

            System.out.println("Total files: " + files.size());
            System.out.println("\nQuery match counts:");

            for (Map.Entry<String, Integer> entry
                    : counts.entrySet()) {
                System.out.printf("%-32s %d%n",
                        entry.getKey(), entry.getValue());
            }
        }
    }
}

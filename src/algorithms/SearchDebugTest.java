
package algorithms;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.stream.Stream;

public class SearchDebugTest {

    public static void main(String[] args) throws Exception {

        Path root = Path.of("corpus", "student-records")
                .toAbsolutePath().normalize();

        int totalFiles = 0;
        int pythonMatches = 0;
        int kmpMatches = 0;
        int rabinKarpMatches = 0;
        int naiveMatches = 0;

        try (Stream<Path> paths = Files.walk(root)) {
            for (Path file : paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase(Locale.ROOT)
                            .endsWith(".txt"))
                    .toList()) {

                String text = Files.readString(file)
                        .toLowerCase(Locale.ROOT);

                totalFiles++;

                boolean kmp = KMP.search(text, "python");
                boolean rk = RabinKarp.search(text, "python");
                boolean naive =
                        NaivePatternMatching.search(text, "python") >= 0;

                if (text.contains("python")) {
                    pythonMatches++;
                }
                if (kmp) {
                    kmpMatches++;
                }
                if (rk) {
                    rabinKarpMatches++;
                }
                if (naive) {
                    naiveMatches++;
                }

                if (totalFiles <= 3) {
                    System.out.println("\nFile: " + file.getFileName());
                    System.out.println("Contains python: "
                            + text.contains("python"));
                    System.out.println("KMP: " + kmp);
                    System.out.println("Rabin-Karp: " + rk);
                    System.out.println("Naive: " + naive);
                }
            }
        }

        System.out.println("\n===== DEBUG SUMMARY =====");
        System.out.println("Total files: " + totalFiles);
        System.out.println("String contains: " + pythonMatches);
        System.out.println("KMP matches: " + kmpMatches);
        System.out.println("Rabin-Karp matches: " + rabinKarpMatches);
        System.out.println("Naive matches: " + naiveMatches);
    }
}

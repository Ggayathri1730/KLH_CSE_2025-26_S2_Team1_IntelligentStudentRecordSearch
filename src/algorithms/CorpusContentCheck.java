
package algorithms;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.stream.Stream;

public class CorpusContentCheck {

    public static void main(String[] args) throws Exception {
        Path root = Path.of("corpus", "student-records");

        try (Stream<Path> paths = Files.walk(root)) {
            paths.filter(Files::isRegularFile)
                 .filter(p -> p.toString().toLowerCase(Locale.ROOT)
                         .endsWith(".txt"))
                 .forEach(file -> {
                     try {
                         String content = Files.readString(file);
                         String lower = content.toLowerCase(Locale.ROOT);

                         if (lower.contains("python")) {
                             int index = lower.indexOf("python");
                             int start = Math.max(0, index - 50);
                             int end = Math.min(
                                     content.length(), index + 80);

                             System.out.println(
                                     "\nFile: " + file.getFileName());
                             System.out.println(
                                     content.substring(start, end)
                                             .replace("\n", " "));
                         }
                     } catch (Exception e) {
                         System.out.println(
                                 "Could not read " + file + ": "
                                         + e.getMessage());
                     }
                 });
        }
    }
}

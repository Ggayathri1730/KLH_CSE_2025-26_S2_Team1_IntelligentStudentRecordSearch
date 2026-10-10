package algorithms;

public class EditDistanceTest {
    public static void main(String[] args) {

        String[] queries = {
            "Java",
            "Jvaa",
            "Python",
            "Pythn",
            "Machine Learning",
            "Machne Learning",
            "Data Science",
            "Data Scince"
        };

        String[] keywords = {
            "Java",
            "Python",
            "Machine Learning",
            "Data Science"
        };

        System.out.println("======================================");
        System.out.println("       EDIT DISTANCE TEST");
        System.out.println("======================================");

        for (int i = 0; i < queries.length; i++) {

            String query = queries[i];

            System.out.println("\nQuery: " + query);

            String closestWord = "";
            int smallestDistance = Integer.MAX_VALUE;

            for (int j = 0; j < keywords.length; j++) {

                int distance =
                    EditDistance.calculate(query, keywords[j]);

                System.out.println(
                    "  " + keywords[j] +
                    " -> Distance: " + distance
                );

                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    closestWord = keywords[j];
                }
            }

            System.out.println(
                "Closest Match: " + closestWord
            );

            System.out.println(
                "Minimum Distance: " + smallestDistance
            );
        }

        System.out.println("\n======================================");
        System.out.println("          TEST COMPLETED");
        System.out.println("======================================");
    }
}

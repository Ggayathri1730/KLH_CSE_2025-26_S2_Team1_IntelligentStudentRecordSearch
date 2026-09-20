package algorithms;

public class FordFulkersonTest {

    public static void main(String[] args) {
        int[][] capacity = {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        int expected = 23;
        int actual = FordFulkerson.maxFlow(capacity, 0, 5);

        if (actual != expected) {
            throw new AssertionError(
                    "Ford-Fulkerson failed. Expected " + expected + " but got " + actual);
        }

        System.out.println("Ford-Fulkerson Test: PASSED");
        System.out.println("Maximum Flow = " + actual);
    }
}

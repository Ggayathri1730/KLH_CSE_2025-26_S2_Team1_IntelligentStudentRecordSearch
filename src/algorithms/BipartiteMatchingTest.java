package algorithms;

import java.util.Map;

public class BipartiteMatchingTest {

    public static void main(String[] args) {
        String[] students = {
                "Asha",
                "Bharat",
                "Charan",
                "Divya",
                "Eshan"
        };

        String[] projects = {
                "AI Chatbot",
                "IoT Monitor",
                "Web Portal",
                "Data Analytics"
        };

        boolean[][] eligibility = {
                {true,  false, true,  false},
                {true,  true,  false, false},
                {false, true,  false, true },
                {false, false, true,  false},
                {false, false, false, true }
        };

        BipartiteMatching.Result result =
                BipartiteMatching.matchStudentsToProjects(
                        students, projects, eligibility);

        int expected = 4;
        if (result.getMaximumMatches() != expected) {
            throw new AssertionError(
                    "Bipartite matching failed. Expected "
                            + expected + " but got " + result.getMaximumMatches());
        }

        System.out.println("Bipartite Matching Test: PASSED");
        System.out.println("Maximum student-project matches = "
                + result.getMaximumMatches());
        System.out.println("Assignments:");

        for (Map.Entry<String, String> entry : result.getMatches().entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("Unmatched students: " + result.getUnmatchedStudents());
    }
}

package algorithms;

public class EditDistanceTest {

    public static void main(String[] args) {

        String str1 = "kitten";
        String str2 = "sitting";

        int distance = EditDistance.calculate(str1, str2);

        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("Edit Distance: " + distance);
    }
}
        System.out.println("          TEST COMPLETED");
        System.out.println("======================================");
    }
}

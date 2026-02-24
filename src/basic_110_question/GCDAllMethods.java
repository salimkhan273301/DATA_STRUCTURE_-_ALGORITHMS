package basic_110_question;

import java.util.Arrays;

public class GCDAllMethods {

    // 1️⃣ Brute Force Method
    public static int gcdBruteForce(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        int min = Math.min(a, b);

        for (int i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }

    // 2️⃣ Euclidean Algorithm (Iterative) ✅ Best
    public static int gcdEuclidean(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 3️⃣ Euclidean Algorithm (Recursive)
    public static int gcdRecursive(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (b == 0) return a;
        return gcdRecursive(b, a % b);
    }

    // 4️⃣ Binary GCD (Stein's Algorithm)
    public static int gcdBinary(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (a == 0) return b;
        if (b == 0) return a;

        if ((a & 1) == 0 && (b & 1) == 0)
            return 2 * gcdBinary(a >> 1, b >> 1);

        if ((a & 1) == 0)
            return gcdBinary(a >> 1, b);

        if ((b & 1) == 0)
            return gcdBinary(a, b >> 1);

        if (a >= b)
            return gcdBinary((a - b) >> 1, b);

        return gcdBinary((b - a) >> 1, a);
    }

    // 5️⃣ GCD of Multiple Numbers (Java 8 Style)
    public static int gcdMultiple(int... numbers) {
        return Arrays.stream(numbers)
                .reduce(0, GCDAllMethods::gcdEuclidean);
    }

    // 6️⃣ LCM using GCD (Safe for overflow)
    public static long lcm(int a, int b) {
        return Math.abs((long)a * b) / gcdEuclidean(a, b);
    }

    public static void main(String[] args) {

        int num1 = 48, num2 = 18;

        System.out.println("Numbers: " + num1 + ", " + num2);

        System.out.println("Brute Force GCD: " + gcdBruteForce(num1, num2));
        System.out.println("Euclidean GCD: " + gcdEuclidean(num1, num2));
        System.out.println("Recursive GCD: " + gcdRecursive(num1, num2));
        System.out.println("Binary GCD: " + gcdBinary(num1, num2));

        System.out.println("LCM: " + lcm(num1, num2));

        System.out.println("GCD of (48,18,30): " +
                gcdMultiple(48, 18, 30));
    }
}
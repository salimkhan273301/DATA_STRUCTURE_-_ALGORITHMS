package basic_110_question;

import java.util.Arrays;

public class PrimeNumberAllMethods {

    // 1️⃣ Brute Force (Check till n-1)
    public static boolean isPrime1(int n) {
        if (n <= 1) return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 2️⃣ Check till n/2
    public static boolean isPrime2(int n) {
        if (n <= 1) return false;

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 3️⃣ Check till √n
    public static boolean isPrime3(int n) {
        if (n <= 1) return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 4️⃣ √n (Skip Even Numbers)
    public static boolean isPrime4(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 5️⃣ 6k ± 1 Optimization (Best Single Check)
    public static boolean isPrime5(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    // 6️⃣ Sieve of Eratosthenes (For Range)
    public static void sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }

        System.out.print("Prime numbers up to " + n + ": ");
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {

        int[] numbers = {2, 3, 4, 5, 16, 17, 18, 19, 20};

        System.out.println("---- Single Number Prime Checks ----");

        for (int num : numbers) {
            System.out.println("Number: " + num);
            System.out.println("Method1 (Brute): " + isPrime1(num));
            System.out.println("Method2 (n/2): " + isPrime2(num));
            System.out.println("Method3 (√n): " + isPrime3(num));
            System.out.println("Method4 (Skip Even): " + isPrime4(num));
            System.out.println("Method5 (6k±1): " + isPrime5(num));
            System.out.println("------------------------------");
        }

        System.out.println("\n---- Sieve Method ----");
        sieve(50);
    }
}
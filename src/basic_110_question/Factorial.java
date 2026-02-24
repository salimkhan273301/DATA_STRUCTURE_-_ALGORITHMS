package basic_110_question;
public class Factorial {
    // Iterative approach
    public static long factorialIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    // Recursive approach
    public static long factorialRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
    
    public static void main(String[] args) {
        int number = 5;
        System.out.println("Factorial of " + number + " (Iterative): " + factorialIterative(number));
        System.out.println("Factorial of " + number + " (Recursive): " + factorialRecursive(number));
    }
}
package generalproblem;

public class RecursiveMult {

    public static int multiply(int a, int b) {
        // Ensure smaller number is the multiplier for fewer recursive calls
        if (b > a) {
            return multiply(b, a);
        }

        // Base case
        if (b == 0) {
            return 0;
        }

        // If b is even
        if ((b & 1) == 0) {
            return multiply(a << 1, b >> 1);
        }

        // If b is odd
        return a + multiply(a, b - 1);
    }

    public static void main(String[] args) {
        System.out.println(multiply(13, 9));  // Output: 117
        System.out.println(multiply(7, 6));   // Output: 42
        System.out.println(multiply(0, 5));   // Output: 0
        System.out.println(multiply(1, 100)); // Output: 100
    }
}

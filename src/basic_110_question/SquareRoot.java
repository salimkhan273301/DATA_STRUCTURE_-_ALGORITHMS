package basic_110_question;

public class SquareRoot {
    
    // Solution 1: Using Math.sqrt()
    public static double sqrtUsingBuiltIn(double num) {
        if (num < 0) {
            throw new IllegalArgumentException("Cannot find square root of negative number");
        }
        return Math.sqrt(num);
    }
    
    // Solution 2: Babylonian Method (Newton-Raphson)
    public static double sqrtBabylonian(double num, double precision) {
        if (num < 0) {
            throw new IllegalArgumentException("Cannot find square root of negative number");
        }
        if (num == 0 || num == 1) return num;
        
        double guess = num / 2.0;
        double difference;
        
        do {
            double betterGuess = (guess + num / guess) / 2.0;
            difference = Math.abs(betterGuess - guess);
            guess = betterGuess;
        } while (difference > precision);
        
        return guess;
    }
    
    // Solution 3: Binary Search Method
    public static double sqrtBinarySearch(double num, double precision) {
        if (num < 0) {
            throw new IllegalArgumentException("Cannot find square root of negative number");
        }
        if (num == 0 || num == 1) return num;
        
        double low = 0;
        double high = num;
        double mid = 0;
        
        if (num < 1) {
            high = 1;
        }
        
        while (high - low > precision) {
            mid = (low + high) / 2.0;
            double square = mid * mid;
            
            if (square == num) {
                return mid;
            } else if (square < num) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        return (low + high) / 2.0;
    }
    
    // Solution 4: Integer Square Root (Floor value)
    public static int sqrtIntegerFloor(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Cannot find square root of negative number");
        }
        if (num == 0 || num == 1) return num;
        
        int left = 1, right = num / 2;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (mid <= num / mid) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // Solution 5: Using Newton's Method with integer
    public static int sqrtNewtonInteger(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Cannot find square root of negative number");
        }
        if (num == 0 || num == 1) return num;
        
        long x = num;
        
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        
        return (int) x;
    }
    
    // Solution 6: Using Exponential and Logarithm
    public static double sqrtUsingLog(double num) {
        if (num < 0) {
            throw new IllegalArgumentException("Cannot find square root of negative number");
        }
        if (num == 0) return 0;
        
        return Math.exp(0.5 * Math.log(num));
    }
    
    public static void main(String[] args) {
        double[] testNumbers = {0, 1, 4, 9, 16, 25, 2, 3, 10, 0.25, 100};
        double precision = 0.000001;
        
        for (double num : testNumbers) {
            System.out.println("\n--- Square root of " + num + " ---");
            System.out.println("1. Built-in Math.sqrt: " + sqrtUsingBuiltIn(num));
            System.out.println("2. Babylonian Method: " + sqrtBabylonian(num, precision));
            System.out.println("3. Binary Search: " + sqrtBinarySearch(num, precision));
            
            if (num == Math.floor(num)) {
                int intNum = (int) num;
                System.out.println("4. Integer Floor: " + sqrtIntegerFloor(intNum));
                System.out.println("5. Newton Integer: " + sqrtNewtonInteger(intNum));
            }
            
            System.out.println("6. Using Log: " + sqrtUsingLog(num));
        }
        
        // Precision comparison
        System.out.println("\n--- Precision Comparison for sqrt(2) ---");
        System.out.println("Actual sqrt(2): 1.4142135623730951");
        System.out.println("Babylonian (1e-10): " + sqrtBabylonian(2, 1e-10));
        System.out.println("Binary Search (1e-10): " + sqrtBinarySearch(2, 1e-10));
    }
}
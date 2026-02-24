package basic_110_question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArmstrongAllMethods {

    // 1️⃣ Using Math.pow()
    public static boolean isArmstrong1(int number) {
        int original = number;
        int digits = String.valueOf(number).length();
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }
        return sum == original;
    }

    // 2️⃣ Without Math.pow() (Manual Power Calculation)
    public static boolean isArmstrong2(int number) {
        int original = number;
        int digits = 0;
        int temp = number;

        while (temp > 0) {
            digits++;
            temp /= 10;
        }

        int sum = 0;
        temp = number;

        while (temp > 0) {
            int digit = temp % 10;
            int power = 1;
            for (int i = 0; i < digits; i++) {
                power *= digit;
            }
            sum += power;
            temp /= 10;
        }

        return sum == original;
    }

    // 3️⃣ Recursive Approach
    public static int armstrongRecursive(int number, int digits) {
        if (number == 0) return 0;
        int digit = number % 10;
        return (int)Math.pow(digit, digits) +
                armstrongRecursive(number / 10, digits);
    }

    public static boolean isArmstrong3(int number) {
        int digits = String.valueOf(number).length();
        return armstrongRecursive(number, digits) == number;
    }

    // 4️⃣ Java 8 Stream
    public static boolean isArmstrong4(int number) {
        int digits = String.valueOf(number).length();
        int sum = String.valueOf(number)
                .chars()
                .map(c -> c - '0')
                .map(d -> (int)Math.pow(d, digits))
                .sum();
        return sum == number;
    }

    // 5️⃣ Optimized Version (Avoid String conversion)
    public static boolean isArmstrong5(int number) {
        int original = number;
        int digits = 0;
        int temp = number;

        while (temp != 0) {
            digits++;
            temp /= 10;
        }

        int sum = 0;
        temp = number;

        while (temp != 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }

        return sum == original;
    }

    // 6️⃣ Find Armstrong Numbers in Range
    public static List<Integer> findArmstrongRange(int start, int end) {
        List<Integer> list = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            if (isArmstrong1(i)) {
                list.add(i);
            }
        }
        return list;
    }

    // Java 8 Stream Range
    public static List<Integer> findArmstrongRangeStream(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .filter(ArmstrongAllMethods::isArmstrong1)
                .boxed()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        int number = 153;

        System.out.println("Number: " + number);
        System.out.println("Method1 (Math.pow): " + isArmstrong1(number));
        System.out.println("Method2 (Manual Power): " + isArmstrong2(number));
        System.out.println("Method3 (Recursive): " + isArmstrong3(number));
        System.out.println("Method4 (Stream): " + isArmstrong4(number));
        System.out.println("Method5 (Optimized): " + isArmstrong5(number));

        System.out.println("\nArmstrong numbers between 1 and 1000:");
        System.out.println(findArmstrongRange(1, 1000));

        System.out.println("\nUsing Stream:");
        System.out.println(findArmstrongRangeStream(1, 1000));
    }
}
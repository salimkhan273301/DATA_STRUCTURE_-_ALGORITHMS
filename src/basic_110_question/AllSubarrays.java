package basic_110_question;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllSubarrays {
    
    // Solution 1: Brute Force - Nested Loops
    public static List<int[]> findAllSubarraysBruteForce(int[] arr) {
        List<int[]> subarrays = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int[] subarray = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    subarray[k - i] = arr[k];
                }
                subarrays.add(subarray);
            }
        }
        
        return subarrays;
    }
    
    // Solution 2: Using Arrays.copyOfRange
    public static List<int[]> findAllSubarraysUsingCopy(int[] arr) {
        List<int[]> subarrays = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                subarrays.add(Arrays.copyOfRange(arr, i, j + 1));
            }
        }
        
        return subarrays;
    }
    
    // Solution 3: Using StringBuilder (for display)
    public static List<String> findAllSubarraysAsString(int[] arr) {
        List<String> subarrays = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < arr.length; j++) {
                if (j > i) sb.append(", ");
                sb.append(arr[j]);
                subarrays.add("[" + sb.toString() + "]");
            }
        }
        
        return subarrays;
    }
    
    // Solution 4: Using Java 8 Streams
    public static List<int[]> findAllSubarraysStreams(int[] arr) {
        return IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(i, arr.length - 1)
                        .mapToObj(j -> Arrays.copyOfRange(arr, i, j + 1)))
                .collect(Collectors.toList());
    }
    
    // Solution 5: Generate only subarrays with specific sum
    public static List<int[]> findSubarraysWithSum(int[] arr, int targetSum) {
        List<int[]> result = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == targetSum) {
                    result.add(Arrays.copyOfRange(arr, i, j + 1));
                }
            }
        }
        
        return result;
    }
    
    // Solution 6: Generate subarrays using recursion
    public static List<int[]> findAllSubarraysRecursive(int[] arr) {
        List<int[]> result = new ArrayList<>();
        generateSubarraysRecursive(arr, 0, result);
        return result;
    }
    
    private static void generateSubarraysRecursive(int[] arr, int start, List<int[]> result) {
        if (start >= arr.length) return;
        
        for (int end = start; end < arr.length; end++) {
            result.add(Arrays.copyOfRange(arr, start, end + 1));
        }
        
        generateSubarraysRecursive(arr, start + 1, result);
    }
    
    // Helper method to print subarrays
    public static void printSubarrays(List<int[]> subarrays) {
        int count = 0;
        for (int[] sub : subarrays) {
            System.out.print(Arrays.toString(sub) + " ");
            count++;
            if (count % 10 == 0) System.out.println();
        }
        System.out.println("\nTotal: " + count);
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        
        System.out.println("Original Array: " + Arrays.toString(arr));
        
        System.out.println("\n1. Brute Force Method:");
        List<int[]> subarrays1 = findAllSubarraysBruteForce(arr);
        printSubarrays(subarrays1);
        
        System.out.println("\n2. Using Arrays.copyOfRange:");
        List<int[]> subarrays2 = findAllSubarraysUsingCopy(arr);
        printSubarrays(subarrays2);
        
        System.out.println("\n3. As Strings:");
        List<String> subarrays3 = findAllSubarraysAsString(arr);
        subarrays3.forEach(s -> System.out.print(s + " "));
        
        System.out.println("\n\n4. Using Streams:");
        List<int[]> subarrays4 = findAllSubarraysStreams(arr);
        printSubarrays(subarrays4);
        
        System.out.println("\n5. Subarrays with sum = 5:");
        List<int[]> subarrays5 = findSubarraysWithSum(arr, 5);
        subarrays5.forEach(sub -> System.out.print(Arrays.toString(sub) + " "));
        
        System.out.println("\n\n6. Using Recursion:");
        List<int[]> subarrays6 = findAllSubarraysRecursive(arr);
        printSubarrays(subarrays6);
    }
}
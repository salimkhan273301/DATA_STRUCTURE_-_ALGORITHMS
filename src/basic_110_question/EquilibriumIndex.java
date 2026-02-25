package basic_110_question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EquilibriumIndex {
    
    // Solution 1: Using Prefix Sum
    public static List<Integer> findEquilibriumIndexPrefixSum(int[] arr) {
        List<Integer> indices = new ArrayList<>();
        
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }
        
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum -= arr[i]; // right sum
            
            if (leftSum == totalSum) {
                indices.add(i);
            }
            
            leftSum += arr[i];
        }
        
        return indices;
    }
    
    // Solution 2: Using Two Arrays
    public static List<Integer> findEquilibriumIndexTwoArrays(int[] arr) {
        List<Integer> indices = new ArrayList<>();
        
        int n = arr.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        
        // Calculate prefix sums
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        
        // Calculate suffix sums
        suffixSum[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + arr[i];
        }
        
        // Check equilibrium
        for (int i = 0; i < n; i++) {
            int leftSum = (i == 0) ? 0 : prefixSum[i - 1];
            int rightSum = (i == n - 1) ? 0 : suffixSum[i + 1];
            
            if (leftSum == rightSum) {
                indices.add(i);
            }
        }
        
        return indices;
    }
    
    // Solution 3: Brute Force
    public static List<Integer> findEquilibriumIndexBruteForce(int[] arr) {
        List<Integer> indices = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            int leftSum = 0, rightSum = 0;
            
            for (int j = 0; j < i; j++) {
                leftSum += arr[j];
            }
            
            for (int j = i + 1; j < arr.length; j++) {
                rightSum += arr[j];
            }
            
            if (leftSum == rightSum) {
                indices.add(i);
            }
        }
        
        return indices;
    }
    
    // Solution 4: Using Streams
    public static List<Integer> findEquilibriumIndexStreams(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        
        return IntStream.range(0, arr.length)
                .filter(i -> {
                    int leftSum = IntStream.range(0, i).map(j -> arr[j]).sum();
                    int rightSum = totalSum - leftSum - arr[i];
                    return leftSum == rightSum;
                })
                .boxed()
                .collect(Collectors.toList());
    }
    
    // Solution 5: Using Cumulative Sum with List
    public static List<Integer> findEquilibriumIndexCumulative(int[] arr) {
        List<Integer> indices = new ArrayList<>();
        List<Integer> cumulativeSums = new ArrayList<>();
        
        int sum = 0;
        cumulativeSums.add(0); // sum before first element
        
        for (int num : arr) {
            sum += num;
            cumulativeSums.add(sum);
        }
        
        int totalSum = sum;
        
        for (int i = 0; i < arr.length; i++) {
            int leftSum = cumulativeSums.get(i);
            int rightSum = totalSum - leftSum - arr[i];
            
            if (leftSum == rightSum) {
                indices.add(i);
            }
        }
        
        return indices;
    }
    
    // Solution 6: For multiple equilibrium queries
    public static class EquilibriumFinder {
        private int[] arr;
        private int[] prefixSum;
        
        public EquilibriumFinder(int[] arr) {
            this.arr = arr;
            this.prefixSum = new int[arr.length];
            prefixSum[0] = arr[0];
            for (int i = 1; i < arr.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }
        }
        
        public boolean isEquilibrium(int index) {
            if (index < 0 || index >= arr.length) return false;
            
            int leftSum = (index == 0) ? 0 : prefixSum[index - 1];
            int rightSum = prefixSum[arr.length - 1] - prefixSum[index];
            
            return leftSum == rightSum;
        }
        
        public List<Integer> findAll() {
            List<Integer> indices = new ArrayList<>();
            int totalSum = prefixSum[arr.length - 1];
            
            for (int i = 0; i < arr.length; i++) {
                int leftSum = (i == 0) ? 0 : prefixSum[i - 1];
                if (leftSum == totalSum - leftSum - arr[i]) {
                    indices.add(i);
                }
            }
            
            return indices;
        }
    }
    
    public static void main(String[] args) {
        int[][] testArrays = {
            {-7, 1, 5, 2, -4, 3, 0},
            {1, 2, 3, 4, 5, 6},
            {1, 2, 3, 3, 2, 1},
            {2, 4, 6, 8, 6, 4, 2},
            {0, 0, 0, 0, 0}
        };
        
        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            System.out.println("\n--- Test Array " + (i + 1) + ": " + Arrays.toString(arr) + " ---");
            
            System.out.println("1. Prefix Sum Method: " + findEquilibriumIndexPrefixSum(arr));
            System.out.println("2. Two Arrays Method: " + findEquilibriumIndexTwoArrays(arr));
            System.out.println("3. Brute Force: " + findEquilibriumIndexBruteForce(arr));
            System.out.println("4. Streams Method: " + findEquilibriumIndexStreams(arr));
            System.out.println("5. Cumulative List: " + findEquilibriumIndexCumulative(arr));
            
            EquilibriumFinder finder = new EquilibriumFinder(arr);
            System.out.println("6. Equilibrium Finder Class: " + finder.findAll());
            
            // Test specific indices
            if (arr.length > 0) {
                System.out.println("   Is index 2 equilibrium? " + finder.isEquilibrium(2));
            }
        }
    }
}
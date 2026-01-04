package generalproblem;
import java.util.*;
import java.util.stream.Collectors;

public class MajorityElementFinder {

    // 1. Original Streams Approach
    public static ArrayList<Integer> majorityElementsStream(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> freq = Arrays.stream(arr).boxed()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        
        return freq.entrySet().stream()
                .filter(e -> e.getValue() > n/3)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // 2. Extended Boyer-Moore Algorithm (Optimal)
    public static ArrayList<Integer> majorityElementsBoyerMoore(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        
        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;
        
        // First pass
        for (int num : nums) {
            if (candidate1 != null && candidate1 == num) {
                count1++;
            } else if (candidate2 != null && candidate2 == num) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        
        // Second pass for verification
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 != null && num == candidate1) count1++;
            if (candidate2 != null && num == candidate2) count2++;
        }
        
        int n = nums.length;
        if (count1 > n/3) result.add(candidate1);
        if (count2 > n/3) result.add(candidate2);
        
        Collections.sort(result);
        return result;
    }

    // 3. Sorting Approach
    public static ArrayList<Integer> majorityElementsSort(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        if (arr.length == 0) return result;
        
        Arrays.sort(arr);
        int n = arr.length;
        int threshold = n/3;
        int count = 1;
        
        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i-1]) {
                count++;
            } else {
                if (count > threshold) {
                    result.add(arr[i-1]);
                }
                count = 1;
            }
        }
        
        if (count > threshold) {
            result.add(arr[n-1]);
        }
        
        return result;
    }

    // 4. Frequency Array Approach (for limited range)
    public static ArrayList<Integer> majorityElementsFreqArray(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        if (arr.length == 0) return result;
        
        // Assuming numbers between -100 to 100 (adjust as needed)
        int[] freq = new int[201];
        for (int num : arr) {
            freq[num + 100]++; // Shift to handle negative numbers
        }
        
        int threshold = arr.length/3;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > threshold) {
                result.add(i - 100); // Shift back
            }
        }
        
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {3, 2, 3},
            {1, 1, 1, 3, 3, 2, 2, 2},
            {1, 2},
            {1},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {2, 2, 1, 3},
            {0, 0, 0, 1, 1, 1, 2}
        };

        String[] methodNames = {
            "Streams Approach",
            "Boyer-Moore Algorithm",
            "Sorting Approach",
            "Frequency Array"
        };

        for (int[] testCase : testCases) {
            System.out.println("\nTest Case: " + Arrays.toString(testCase));
            
            ArrayList<Integer> result1 = majorityElementsStream(testCase);
            ArrayList<Integer> result2 = majorityElementsBoyerMoore(testCase);
            ArrayList<Integer> result3 = majorityElementsSort(testCase);
            ArrayList<Integer> result4 = majorityElementsFreqArray(testCase);
            
            ArrayList<Integer>[] results = new ArrayList[]{result1, result2, result3, result4};
            
            for (int i = 0; i < results.length; i++) {
                System.out.printf("%-20s: %s\n", methodNames[i], results[i]);
            }
        }
    }
}
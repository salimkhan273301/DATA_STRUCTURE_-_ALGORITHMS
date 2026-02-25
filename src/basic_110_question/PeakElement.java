package basic_110_question;

import java.util.*;
import java.util.stream.IntStream;

public class PeakElement {
    
    // Solution 1: Linear Scan
    public static Integer findPeakLinear(int[] arr) {
        if (arr.length == 0) return null;
        if (arr.length == 1) return 0;
        
        // Check first element
        if (arr[0] >= arr[1]) return 0;
        
        // Check middle elements
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1]) {
                return i;
            }
        }
        
        // Check last element
        if (arr[arr.length - 1] >= arr[arr.length - 2]) {
            return arr.length - 1;
        }
        
        return null;
    }
    
    // Solution 2: Binary Search (for any peak)
    public static Integer findPeakBinarySearch(int[] arr) {
        if (arr.length == 0) return null;
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // Solution 3: Find all peaks
    public static List<Integer> findAllPeaks(int[] arr) {
        List<Integer> peaks = new ArrayList<>();
        
        if (arr.length == 0) return peaks;
        
        // Check first element
        if (arr.length == 1 || arr[0] >= arr[1]) {
            peaks.add(0);
        }
        
        // Check middle elements
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1]) {
                peaks.add(i);
            }
        }
        
        // Check last element
        if (arr.length > 1 && arr[arr.length - 1] >= arr[arr.length - 2]) {
            peaks.add(arr.length - 1);
        }
        
        return peaks;
    }
    
    // Solution 4: Recursive Binary Search
    public static Integer findPeakRecursive(int[] arr) {
        return findPeakRecursiveHelper(arr, 0, arr.length - 1);
    }
    
    private static Integer findPeakRecursiveHelper(int[] arr, int left, int right) {
        if (left == right) return left;
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] < arr[mid + 1]) {
            return findPeakRecursiveHelper(arr, mid + 1, right);
        } else {
            return findPeakRecursiveHelper(arr, left, mid);
        }
    }
    
    // Solution 5: Using Streams
    public static OptionalInt findPeakStreams(int[] arr) {
        return IntStream.range(0, arr.length)
                .filter(i -> {
                    if (i == 0) return arr.length == 1 || arr[i] >= arr[i + 1];
                    if (i == arr.length - 1) return arr[i] >= arr[i - 1];
                    return arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1];
                })
                .findFirst();
    }
    
    // Solution 6: Find peak in 2D matrix
    public static int[] findPeak2D(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int leftCol = 0;
        int rightCol = cols - 1;
        
        while (leftCol <= rightCol) {
            int midCol = leftCol + (rightCol - leftCol) / 2;
            
            // Find maximum in middle column
            int maxRow = 0;
            for (int i = 1; i < rows; i++) {
                if (matrix[i][midCol] > matrix[maxRow][midCol]) {
                    maxRow = i;
                }
            }
            
            // Check if it's a peak
            boolean isPeak = true;
            if (midCol > 0 && matrix[maxRow][midCol] < matrix[maxRow][midCol - 1]) {
                isPeak = false;
                rightCol = midCol - 1;
            } else if (midCol < cols - 1 && matrix[maxRow][midCol] < matrix[maxRow][midCol + 1]) {
                isPeak = false;
                leftCol = midCol + 1;
            }
            
            if (isPeak) {
                return new int[]{maxRow, midCol};
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        int[][] testArrays = {
            {1, 2, 3, 1},
            {1, 2, 1, 3, 5, 6, 4},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {1, 3, 5, 7, 9, 8, 6, 4, 2},
            {1, 1, 1, 1, 1}
        };
        
        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            System.out.println("\n--- Test Array " + (i + 1) + ": " + Arrays.toString(arr) + " ---");
            
            System.out.println("1. Linear Scan Peak Index: " + findPeakLinear(arr));
            System.out.println("2. Binary Search Peak: " + findPeakBinarySearch(arr));
            System.out.println("3. All Peaks: " + findAllPeaks(arr));
            System.out.println("4. Recursive Peak: " + findPeakRecursive(arr));
            
            OptionalInt peakStream = findPeakStreams(arr);
            System.out.println("5. Streams Peak: " + 
                (peakStream.isPresent() ? peakStream.getAsInt() : "none"));
        }
        
        // Test 2D peak
        System.out.println("\n--- 2D Peak Finding ---");
        int[][] matrix = {
            {10, 8, 10, 10},
            {14, 13, 12, 11},
            {15, 9, 11, 21},
            {16, 17, 19, 20}
        };
        
        System.out.println("2D Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        
        int[] peak2D = findPeak2D(matrix);
        if (peak2D != null) {
            System.out.println("6. Peak found at [" + peak2D[0] + ", " + peak2D[1] + 
                             "] = " + matrix[peak2D[0]][peak2D[1]]);
        }
    }
}
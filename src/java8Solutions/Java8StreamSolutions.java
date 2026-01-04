package java8Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8StreamSolutions {

    // ==================== SOLUTION 1: Basic Stream Approach ====================
    public static ArrayList<Integer> streamApproach1(int[] arr, int target) {
        return IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.range(i, arr.length)
                        .mapToObj(j -> new int[]{i, j, Arrays.stream(arr, i, j + 1).sum()}))
                .filter(pair -> pair[2] == target)
                .findFirst()
                .map(pair -> new ArrayList<>(Arrays.asList(pair[0] + 1, pair[1] + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }

    // ==================== SOLUTION 2: Optimized Stream with Precomputed Sums ====================
	/*
	 * public static ArrayList<Integer> streamApproach2(int[] arr, int target) {
	 * int[] prefixSum = new int[arr.length + 1]; Arrays.parallelPrefix(arr,
	 * prefixSum, 1, (a, b) -> a + b);
	 * 
	 * return IntStream.range(0, arr.length) .boxed() .flatMap(i ->
	 * IntStream.range(i, arr.length) .mapToObj(j -> new int[]{i, j, prefixSum[j +
	 * 1] - prefixSum[i]})) .filter(pair -> pair[2] == target) .findFirst()
	 * .map(pair -> new ArrayList<>(Arrays.asList(pair[0] + 1, pair[1] + 1)))
	 * .orElse(new ArrayList<>(Arrays.asList(-1))); }
	 */
    // ==================== SOLUTION 3: Stream with Custom Collector ====================
    public static ArrayList<Integer> streamApproach3(int[] arr, int target) {
        return IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.range(i, arr.length)
                        .mapToObj(j -> new SubarrayInfo(i, j, Arrays.stream(arr, i, j + 1).sum())))
                .filter(info -> info.sum == target)
                .findFirst()
                .map(info -> new ArrayList<>(Arrays.asList(info.start + 1, info.end + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }

    // ==================== SOLUTION 4: Parallel Stream for Large Arrays ====================
    public static ArrayList<Integer> streamApproach4(int[] arr, int target) {
        return IntStream.range(0, arr.length)
                .parallel()
                .boxed()
                .flatMap(i -> IntStream.range(i, arr.length)
                        .mapToObj(j -> new int[]{i, j, IntStream.range(i, j + 1).map(k -> arr[k]).sum()}))
                .filter(pair -> pair[2] == target)
                .findAny() // findAny for parallel streams
                .map(pair -> new ArrayList<>(Arrays.asList(pair[0] + 1, pair[1] + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }

    // ==================== SOLUTION 5: Stream with TakeWhile (Java 9+) ====================
    public static ArrayList<Integer> streamApproach5(int[] arr, int target) {
        return IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> {
                    int[] currentSum = {0};
                    return IntStream.range(i, arr.length)
                            .takeWhile(j -> {
                                currentSum[0] += arr[j];
                                return currentSum[0] <= target * 2; // Early termination
                            })
                            .mapToObj(j -> new int[]{i, j, currentSum[0]});
                })
                .filter(pair -> pair[2] == target)
                .findFirst()
                .map(pair -> new ArrayList<>(Arrays.asList(pair[0] + 1, pair[1] + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }

    // ==================== SOLUTION 6: Functional Style with Reduce ====================
    public static ArrayList<Integer> streamApproach6(int[] arr, int target) {
        Optional<SubarrayInfo> result = IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.range(i, arr.length)
                        .mapToObj(j -> new SubarrayInfo(i, j, 0)))
                .map(info -> {
                    info.sum = IntStream.rangeClosed(info.start, info.end)
                            .map(k -> arr[k])
                            .sum();
                    return info;
                })
                .filter(info -> info.sum == target)
                .reduce((first, second) -> first); // Get first occurrence

        return result.map(info -> new ArrayList<>(Arrays.asList(info.start + 1, info.end + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }

    // ==================== SOLUTION 7: Stream with Indexed Access ====================
    public static ArrayList<Integer> streamApproach7(int[] arr, int target) {
        return IntStream.range(0, arr.length)
                .mapToObj(i -> {
                    int sum = 0;
                    for (int j = i; j < arr.length; j++) {
                        sum += arr[j];
                        if (sum == target) {
                            return new int[]{i, j};
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .findFirst()
                .map(pair -> new ArrayList<>(Arrays.asList(pair[0] + 1, pair[1] + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }

    // ==================== SOLUTION 8: Stream with Custom Terminal Operation ====================
    public static ArrayList<Integer> streamApproach8(int[] arr, int target) {
        class ResultHolder {
            int start = -1;
            int end = -1;
        }
        
        ResultHolder holder = new ResultHolder();
        
        IntStream.range(0, arr.length)
                .anyMatch(i -> {
                    int sum = 0;
                    for (int j = i; j < arr.length; j++) {
                        sum += arr[j];
                        if (sum == target) {
                            holder.start = i;
                            holder.end = j;
                            return true; // break the stream
                        }
                    }
                    return false;
                });
        
        if (holder.start != -1) {
            return new ArrayList<>(Arrays.asList(holder.start + 1, holder.end + 1));
        }
        return new ArrayList<>(Arrays.asList(-1));
    }

    // ==================== SOLUTION 9: Stream with Collectors ====================
    public static ArrayList<Integer> streamApproach9(int[] arr, int target) {
        List<int[]> results = IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.range(i, arr.length)
                        .mapToObj(j -> new int[]{i, j, IntStream.range(i, j + 1).map(k -> arr[k]).sum()}))
                .filter(pair -> pair[2] == target)
                .collect(Collectors.toList());
        
        if (!results.isEmpty()) {
            int[] first = results.get(0);
            return new ArrayList<>(Arrays.asList(first[0] + 1, first[1] + 1));
        }
        return new ArrayList<>(Arrays.asList(-1));
    }

    // ==================== SOLUTION 10: Most Concise Stream ====================
    public static ArrayList<Integer> streamApproach10(int[] arr, int target) {
        return IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.range(i, arr.length)
                        .filter(j -> IntStream.rangeClosed(i, j).map(k -> arr[k]).sum() == target)
                        .mapToObj(j -> new int[]{i, j}))
                .findFirst()
                .map(pair -> new ArrayList<>(Arrays.asList(pair[0] + 1, pair[1] + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }

    // ==================== SUPPORTING CLASS ====================
    static class SubarrayInfo {
        int start;
        int end;
        int sum;
        
        SubarrayInfo(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    // ==================== TEST METHOD ====================
    public static void testAllStreamApproaches() {
        int[] testArray = {1, 2, 3, 4, 5};
        int target = 9;
        
        System.out.println("Testing all Java 8 Stream approaches:");
        System.out.println("Array: " + Arrays.toString(testArray));
        System.out.println("Target: " + target);
        System.out.println();
        
        testStreamApproach("Approach 1", testArray, target, Java8StreamSolutions::streamApproach1);
     //   testStreamApproach("Approach 2", testArray, target, Java8StreamSolutions::streamApproach2);
        testStreamApproach("Approach 3", testArray, target, Java8StreamSolutions::streamApproach3);
        testStreamApproach("Approach 4", testArray, target, Java8StreamSolutions::streamApproach4);
        testStreamApproach("Approach 7", testArray, target, Java8StreamSolutions::streamApproach7);
        testStreamApproach("Approach 8", testArray, target, Java8StreamSolutions::streamApproach8);
        testStreamApproach("Approach 9", testArray, target, Java8StreamSolutions::streamApproach9);
        testStreamApproach("Approach 10", testArray, target, Java8StreamSolutions::streamApproach10);
    }
    
    private static void testStreamApproach(String name, int[] arr, int target, 
                                         java.util.function.BiFunction<int[], Integer, ArrayList<Integer>> approach) {
        try {
            ArrayList<Integer> result = approach.apply(arr, target);
            System.out.println(name + ": " + result);
        } catch (Exception e) {
            System.out.println(name + ": Error - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        testAllStreamApproaches();
    }
}

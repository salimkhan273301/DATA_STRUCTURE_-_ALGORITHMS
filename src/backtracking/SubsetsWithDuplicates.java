package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubsetsWithDuplicates {
    
    // Solution 1: Backtracking with sorting and skipping duplicates
    public List<List<Integer>> subsetsWithDupBacktrack(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> temp, int[] nums, int start) {
        res.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            backtrack(res, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    // Solution 2: Iterative approach with duplicate handling
    public List<List<Integer>> subsetsWithDupIterative(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int startIndex, endIndex = 0;
        
        for (int i = 0; i < nums.length; i++) {
            startIndex = (i > 0 && nums[i] == nums[i - 1]) ? endIndex : 0;
            endIndex = res.size();
            for (int j = startIndex; j < endIndex; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }

    // Solution 3: Bitmasking with duplicate check
    public List<List<Integer>> subsetsWithDupBitmask(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        int n = nums.length;
        
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    temp.add(nums[i]);
                }
            }
            res.add(temp);
        }
        return new ArrayList<>(res);
    }

    // Solution 4: Recursive without backtracking
    public List<List<Integer>> subsetsWithDupRecursive(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void generateSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[index]);
        generateSubsets(nums, index + 1, current, res);
        current.remove(current.size() - 1);
        while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            index++;
        }
        generateSubsets(nums, index + 1, current, res);
    }

    // Solution 5: Using Java Streams (not recommended for interviews)
    public List<List<Integer>> subsetsWithDupStream(int[] nums) {
        Arrays.sort(nums);
        return IntStream.range(0, 1 << nums.length)
                .mapToObj(mask -> IntStream.range(0, nums.length)
                        .filter(i -> (mask & (1 << i)) != 0)
                        .mapToObj(i -> nums[i])
                        .collect(Collectors.toList()))
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SubsetsWithDuplicates solution = new SubsetsWithDuplicates();
        int[] nums = {1, 2, 2};
        
        System.out.println("Backtracking Solution:");
        System.out.println(solution.subsetsWithDupBacktrack(nums));
        
        System.out.println("\nIterative Solution:");
        System.out.println(solution.subsetsWithDupIterative(nums));
        
        System.out.println("\nBitmask Solution:");
        System.out.println(solution.subsetsWithDupBitmask(nums));
        
        System.out.println("\nRecursive Solution:");
        System.out.println(solution.subsetsWithDupRecursive(nums));
        
        System.out.println("\nStream Solution:");
        System.out.println(solution.subsetsWithDupStream(nums));
    }
}
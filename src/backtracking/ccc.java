package backtracking;

import java.util.*;

class Permutations {
    
    // Main method to test all solutions
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3};
        int[] test2 = {0, 1};
        int[] test3 = {1};
        
        System.out.println("Testing all permutation methods:");
        System.out.println("Input: " + Arrays.toString(test1));
        
        for (int i = 1; i <= 10; i++) {
            System.out.println("\nMethod " + i + ":");
            List<List<Integer>> result = null;
            long startTime = System.nanoTime();
            
            switch (i) {
                case 1: result = permute1(test1.clone()); break;
                case 2: result = permute2(test1.clone()); break;
                case 3: result = permute3(test1.clone()); break;
                case 4: result = permute4(test1.clone()); break;
                case 5: result = permute5(test1.clone()); break;
                case 6: result = permute6(test1.clone()); break;
                case 7: result = permute7(test1.clone()); break;
                case 8: result = permute8(test1.clone()); break;
                case 9: result = permute9(test1.clone()); break;
                case 10: result = permute10(test1.clone()); break;
            }
            
            long endTime = System.nanoTime();
            System.out.println("Time: " + (endTime - startTime) + " ns");
            System.out.println("Count: " + result.size());
            // System.out.println("Result: " + result);
        }
    }

    // Method 1: Backtracking with swapping (Optimal)
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackSwap(nums, 0, res);
        return res;
    }
    
    private static void backtrackSwap(int[] arr, int idx, List<List<Integer>> res) {
        if (idx == arr.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : arr) list.add(num);
            res.add(list);
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            swap(arr, idx, i);
            backtrackSwap(arr, idx + 1, res);
            swap(arr, idx, i);
        }
    }

    // Method 2: Backtracking with boolean array
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), new boolean[nums.length], res);
        return res;
    }
    
    private static void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> res) {
        if (current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                backtrack(nums, current, used, res);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    // Method 3: Iterative BFS approach
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute3(int[] nums) {
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>());
        
        for (int num : nums) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<Integer> current = queue.poll();
                for (int j = 0; j <= current.size(); j++) {
                    List<Integer> newPerm = new ArrayList<>(current);
                    newPerm.add(j, num);
                    queue.offer(newPerm);
                }
            }
        }
        return new ArrayList<>(queue);
    }

    // Method 4: Heap's Algorithm
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute4(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        heapsAlgorithm(nums, nums.length, res);
        return res;
    }
    
    private static void heapsAlgorithm(int[] arr, int size, List<List<Integer>> res) {
        if (size == 1) {
            List<Integer> list = new ArrayList<>();
            for (int num : arr) list.add(num);
            res.add(list);
            return;
        }
        for (int i = 0; i < size; i++) {
            heapsAlgorithm(arr, size - 1, res);
            if (size % 2 == 1) {
                swap(arr, 0, size - 1);
            } else {
                swap(arr, i, size - 1);
            }
        }
    }

    // Method 5: Next Permutation (Lexicographical order)
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute5(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        do {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) list.add(num);
            res.add(list);
        } while (nextPermutation(nums));
        return res;
    }
    
    private static boolean nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i < 0) return false;
        
        int j = nums.length - 1;
        while (nums[j] <= nums[i]) j--;
        
        swap(nums, i, j);
        reverse(nums, i + 1);
        return true;
    }

    // Method 6: Using DFS with path tracking
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute6(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, new ArrayList<>(), new HashSet<>(), res);
        return res;
    }
    
    private static void dfs(int[] nums, List<Integer> path, Set<Integer> visited, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int num : nums) {
            if (!visited.contains(num)) {
                visited.add(num);
                path.add(num);
                dfs(nums, path, visited, res);
                path.remove(path.size() - 1);
                visited.remove(num);
            }
        }
    }

    // Method 7: Using recursive list building
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute7(int[] nums) {
        if (nums.length == 0) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());
            return res;
        }
        
        List<List<Integer>> result = new ArrayList<>();
        int first = nums[0];
        int[] rest = Arrays.copyOfRange(nums, 1, nums.length);
        
        for (List<Integer> perm : permute7(rest)) {
            for (int i = 0; i <= perm.size(); i++) {
                List<Integer> newPerm = new ArrayList<>(perm);
                newPerm.add(i, first);
                result.add(newPerm);
            }
        }
        return result;
    }

    // Method 8: Using iterative insertion
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute8(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        
        for (int num : nums) {
            List<List<Integer>> newRes = new ArrayList<>();
            for (List<Integer> list : res) {
                for (int i = 0; i <= list.size(); i++) {
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(i, num);
                    newRes.add(newList);
                }
            }
            res = newRes;
        }
        return res;
    }

    // Method 9: Using bitmask for tracking used elements
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute9(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackBitmask(nums, 0, new ArrayList<>(), 0, res);
        return res;
    }
    
    private static void backtrackBitmask(int[] nums, int mask, List<Integer> current, int count, List<List<Integer>> res) {
        if (count == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) == 0) {
                current.add(nums[i]);
                backtrackBitmask(nums, mask | (1 << i), current, count + 1, res);
                current.remove(current.size() - 1);
            }
        }
    }

    // Method 10: Using iterative with stack
    // Time: O(n * n!), Space: O(n!)
    public static List<List<Integer>> permute10(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new ArrayList<>());
        
        while (!stack.isEmpty()) {
            List<Integer> current = stack.pop();
            if (current.size() == nums.length) {
                res.add(current);
                continue;
            }
            for (int num : nums) {
                if (!current.contains(num)) {
                    List<Integer> newPerm = new ArrayList<>(current);
                    newPerm.add(num);
                    stack.push(newPerm);
                }
            }
        }
        return res;
    }

    // Utility methods
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static void reverse(int[] a, int start) {
        int end = a.length - 1;
        while (start < end) {
            swap(a, start, end);
            start++;
            end--;
        }
    }
}

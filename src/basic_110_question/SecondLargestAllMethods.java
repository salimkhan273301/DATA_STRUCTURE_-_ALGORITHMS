package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class SecondLargestAllMethods {

    // 1️⃣ Single Pass (Best Approach - O(n))
    public static Integer method1(int[] arr) {
        if (arr == null || arr.length < 2) return null;

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }

        return second == Integer.MIN_VALUE ? null : second;
    }

    // 2️⃣ Using Sorting (O(n log n))
    public static Integer method2(int[] arr) {
        if (arr == null || arr.length < 2) return null;

        int[] copy = arr.clone();
        Arrays.sort(copy);

        int largest = copy[copy.length - 1];

        for (int i = copy.length - 2; i >= 0; i--) {
            if (copy[i] < largest) {
                return copy[i];
            }
        }

        return null;
    }

    // 3️⃣ Using Java 8 Stream
    public static Integer method3(int[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    // 4️⃣ Using Set (Remove duplicates first)
    public static Integer method4(int[] arr) {
        Set<Integer> set = new TreeSet<>();
        for (int num : arr) {
            set.add(num);
        }

        if (set.size() < 2) return null;

        List<Integer> list = new ArrayList<>(set);
        return list.get(list.size() - 2);
    }

    // 5️⃣ Using PriorityQueue (Max Heap)
    public static Integer method5(int[] arr) {
        if (arr == null || arr.length < 2) return null;

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : arr) {
            if (!maxHeap.contains(num)) {
                maxHeap.offer(num);
            }
        }

        maxHeap.poll(); // remove largest
        return maxHeap.peek();
    }

    // 6️⃣ Second Smallest (Bonus)
    public static Integer secondSmallest(int[] arr) {
        if (arr == null || arr.length < 2) return null;

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num < first) {
                second = first;
                first = num;
            } else if (num < second && num != first) {
                second = num;
            }
        }

        return second == Integer.MAX_VALUE ? null : second;
    }

    public static void main(String[] args) {

        int[] arr = {12, 35, 1, 10, 34, 1};

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("--------------------------------");

        System.out.println("Method1 (Single Pass): " + method1(arr));
        System.out.println("Method2 (Sorting): " + method2(arr));
        System.out.println("Method3 (Stream): " + method3(arr));
        System.out.println("Method4 (Set): " + method4(arr));
        System.out.println("Method5 (Heap): " + method5(arr));

        System.out.println("Second Smallest: " + secondSmallest(arr));
    }
}
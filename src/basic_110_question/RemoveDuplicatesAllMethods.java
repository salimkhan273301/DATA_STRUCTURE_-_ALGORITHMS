package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicatesAllMethods {

    // 1️⃣ Using LinkedHashSet (Maintains insertion order)
    public static int[] method1(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }
        return result;
    }

    // 2️⃣ Using HashSet (Order not guaranteed)
    public static int[] method2(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }
        return result;
    }

    // 3️⃣ Two Pointer (Sorted Array Only)
    public static int[] method3(int[] arr) {
        if (arr.length <= 1) return arr;

        int[] temp = new int[arr.length];
        int j = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                temp[j++] = arr[i];
            }
        }
        temp[j++] = arr[arr.length - 1];

        return Arrays.copyOf(temp, j);
    }

    // 4️⃣ In-place Remove Duplicates (Sorted Array - O(1) space)
    public static int method4(int[] arr) {
        if (arr.length == 0) return 0;

        int j = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                arr[++j] = arr[i];
            }
        }

        return j + 1; // new length
    }

    // 5️⃣ Using Java 8 Stream
    public static int[] method5(int[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .toArray();
    }

    // 6️⃣ Using Frequency Map
    public static int[] method6(int[] arr) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] result = new int[map.size()];
        int index = 0;

        for (int key : map.keySet()) {
            result[index++] = key;
        }

        return result;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5, 6, 6};

        System.out.println("Original: " + Arrays.toString(arr));
        System.out.println("--------------------------------");

        System.out.println("Method1 (LinkedHashSet): " + Arrays.toString(method1(arr)));
        System.out.println("Method2 (HashSet): " + Arrays.toString(method2(arr)));
        System.out.println("Method5 (Stream): " + Arrays.toString(method5(arr)));
        System.out.println("Method6 (Frequency Map): " + Arrays.toString(method6(arr)));

        // Sorted example
        int[] sortedArr = {1, 1, 2, 2, 3, 4, 4, 5, 5, 6};

        System.out.println("\nSorted Array: " + Arrays.toString(sortedArr));
        System.out.println("Method3 (Two Pointer): " + Arrays.toString(method3(sortedArr)));

        int newLength = method4(sortedArr);
        System.out.println("Method4 (In-place) New Length: " + newLength);
        System.out.println("Array after In-place: " +
                Arrays.toString(Arrays.copyOf(sortedArr, newLength)));
    }
}
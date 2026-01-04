package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class MaxSumofaPairWithEqualSumofDigitsDemo {
    public int maximumSum(int[] nums) {
        return Arrays.stream(nums)
            .boxed()
            .collect(Collectors.groupingBy(
                this::getDigitSum,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        if (list.size() < 2) return -1;
                        Collections.sort(list, Collections.reverseOrder());
                        return list.get(0) + list.get(1);
                    }
                )
            ))
            .values()
            .stream()
            .filter(sum -> sum != -1)
            .max(Integer::compare)
            .orElse(-1);
    }

    private int getDigitSum(int num) {
        return String.valueOf(num)
            .chars()
            .map(c -> c - '0')
            .sum();
    }
    
    
    public static void main(String[] args) {
    	MaxSumofaPairWithEqualSumofDigitsDemo sol = new MaxSumofaPairWithEqualSumofDigitsDemo();
        System.out.println(sol.maximumSum(new int[]{18, 43, 36, 13, 7})); // 54
        System.out.println(sol.maximumSum(new int[]{10, 12, 19, 14}));    // -1
        System.out.println(sol.maximumSum(new int[]{9, 81, 72, 63}));     // 153
    }
}
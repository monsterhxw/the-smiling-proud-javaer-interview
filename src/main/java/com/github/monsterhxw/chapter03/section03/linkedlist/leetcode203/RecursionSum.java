package com.github.monsterhxw.chapter03.section03.linkedlist.leetcode203;

/**
 * @author Xuewei Huang
 * @created 2022-04-19
 */
public class RecursionSum {

    public static int sum(int[] nums) {
        return sum(nums, 0);
    }

    private static int sum(int[] nums, int left) {
        if (left == nums.length) {
            return 0;
        }
        return nums[left] + sum(nums, left + 1);
    }
}

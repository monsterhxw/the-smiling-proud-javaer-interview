package com.github.monsterhxw.external.binarysearch;

/**
 * @author Xuewei Huang
 * @created 2022-05-03
 */
public class BinarySearchLower {

    /**
     * 小于 target 的 Max index
     */
    public static int lower(int[] nums, int target) {
        int l = -1;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] < target) {
                l = mid;
            } else {
                // nums[mid] >= target
                r = mid - 1;
            }
        }
        return l;
    }
}

package com.github.monsterhxw.external.binarysearch;

/**
 * @author Xuewei Huang
 * @created 2022-05-03
 */
public class BinarySearchUpper {

    private BinarySearchUpper() {
    }

    public static int upper(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        // 在 nums[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

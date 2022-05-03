package com.github.monsterhxw.external.binarysearch;

/**
 * @author Xuewei Huang
 * @created 2022-05-03
 */
public class BinarySearchIterative {

    private BinarySearchIterative() {
    }

    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}

package com.github.monsterhxw.chapter03.section02;

import java.util.Arrays;

class LeetCode75SortColors {

    public void sortColors(int[] nums) {
        // arr[0, lt] < 1 & arr[lt + 1, i - 1] == 1 && arr[gt, length - 1] > 1
        int lt = -1;
        int i = 0;
        int gt = nums.length;
        while (i < gt) {
            if (nums[i] < 1) {
                lt++;
                swap(nums, i, lt);
                i++;
            } else if (nums[i] > 1) {
                gt--;
                swap(nums, i, gt);
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        LeetCode75SortColors solution = new LeetCode75SortColors();
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
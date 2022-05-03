package com.github.monsterhxw.external.binarysearch;

/**
 * @author Xuewei Huang
 * @created 2022-05-03
 */
public class BinarySearchRecursive {

    private BinarySearchRecursive() {
    }

    public static int search(int[] data, int target) {
        return search(data, target, 0, data.length - 1);
    }

    private static int search(int[] data, int target, int l, int r) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (data[mid] == target) {
            return mid;
        } else if (data[mid] < target) {
            return search(data, target, mid + 1, r);
        } else {
            return search(data, target, l, mid - 1);
        }
    }
}

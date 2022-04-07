package com.github.monsterhxw.chapter03.section02;

import java.util.Arrays;

/**
 * @author XueweiHuang
 * @created 2022-04-07
 */
public class MergeSort implements IMutableSorter {

    /**
     * Time complexity: O(nlogn)
     */
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 防溢出，(l + r) / 2 = (l + r - l + l) / 2 = (2l + r - l) / 2 = l + (r - l) / 2;
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, l, mid, r);
        }
    }

    /**
     * 合并两个有序的区间 arr[l, mid] 和 arr[mid + 1, r]
     */
    private void merge(int[] arr, int l, int mid, int r) {
        int[] copyArr = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = copyArr[j - l];
                j++;
            } else if (j > r) {
                arr[k] = copyArr[i - l];
                i++;
            } else if (copyArr[i - l] <= copyArr[j - l]) {
                arr[k] = copyArr[i - l];
                i++;
            } else if (copyArr[i - l] > copyArr[j - l]) {
                arr[k] = copyArr[j - l];
                j++;
            }
        }
    }
}

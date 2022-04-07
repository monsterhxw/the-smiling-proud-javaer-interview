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
        int[] copyArr = Arrays.copyOfRange(arr, 0, arr.length);
        mergeSort(arr, 0, arr.length - 1, copyArr);
    }

    private void mergeSort(int[] arr, int l, int r, int[] copyArr) {
        if (l >= r) {
            return;
        }
        // 防溢出，(l + r) / 2 = (l + r - l + l) / 2 = (2l + r - l) / 2 = l + (r - l) / 2;
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid, copyArr);
        mergeSort(arr, mid + 1, r, copyArr);
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, l, mid, r, copyArr);
        }
    }

    /**
     * 合并两个有序的区间 arr[l, mid] 和 arr[mid + 1, r]
     */
    private void merge(int[] arr, int l, int mid, int r, int[] copyArr) {
        System.arraycopy(arr, l, copyArr, l, r - l + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = copyArr[j];
                j++;
            } else if (j > r) {
                arr[k] = copyArr[i];
                i++;
            } else if (copyArr[i] <= copyArr[j]) {
                arr[k] = copyArr[i];
                i++;
            } else if (copyArr[i] > copyArr[j]) {
                arr[k] = copyArr[j];
                j++;
            }
        }
    }
}

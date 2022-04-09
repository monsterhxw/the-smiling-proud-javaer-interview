package com.github.monsterhxw.chapter03.section02;

/**
 * @author XueweiHuang
 * @created 2022-04-09
 */
public class QuickSort implements IMutableSorter {

    /**
     * Time complexity: O(nlogn)
     */
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int partitionIndex = partition(arr, l, r);
        quickSort(arr, l, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        // arr[l + 1, j] < arr[l] & arr[j + 1, i - 1] >= arr[l]
        int j = l;
        for (int i = j + 1; i <= r; i++) {
            if (arr[i] < arr[l]) {
                if (i > j + 1) {
                    SortHelper.swap(arr, i, j + 1);
                }
                j++;
            }
        }
        SortHelper.swap(arr, l, j);
        return j;
    }
}

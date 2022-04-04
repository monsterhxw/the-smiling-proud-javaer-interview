package com.github.monsterhxw.chapter03.section02;

/**
 * @author XueweiHuang
 * @created 2022-04-04
 */
public class SelectionSort implements IMutableSorter {

    /**
     * Time complexity: O(n^2)
     */
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            SortHelper.swap(arr, i, minIndex);
        }
    }
}

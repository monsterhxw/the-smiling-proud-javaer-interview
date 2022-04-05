package com.github.monsterhxw.chapter03.section02;

/**
 * @author XueweiHuang
 * @created 2022-04-04
 */
public class BubbleSort implements IMutableSorter {

    /**
     * Time complexity: O(n^2)
     */
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i + 1 < arr.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortHelper.swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}

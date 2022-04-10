package com.github.monsterhxw.chapter03.section02;

import java.util.Random;

/**
 * @author XueweiHuang
 * @created 2022-04-10
 */
public class QuickSort2Way implements IMutableSorter {

    private final Random random = new Random();

    /**
     * Time complexity: O(nlogn)
     */
    @Override
    public void sort(int[] arr) {
        quickSort2Way(arr, 0, arr.length - 1);
    }

    private void quickSort2Way(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int partitionIndex = partition2Way(arr, l, r);
        quickSort2Way(arr, l, partitionIndex - 1);
        quickSort2Way(arr, partitionIndex + 1, r);
    }

    private int partition2Way(int[] arr, int l, int r) {
        int p = l + random.nextInt(r - l + 1);
        SortHelper.swap(arr, l, p);
        // arr[l + 1, i - 1] <= v && arr[j + 1, r] >= v
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= j && arr[i] < arr[l]) {
                i++;
            }
            while (j >= i && arr[j] > arr[l]) {
                j--;
            }
            if (i >= j) {
                break;
            }
            SortHelper.swap(arr, i, j);
            i++;
            j--;
        }
        SortHelper.swap(arr, j, l);
        return j;
    }
}

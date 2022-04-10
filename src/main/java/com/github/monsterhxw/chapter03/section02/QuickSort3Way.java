package com.github.monsterhxw.chapter03.section02;

import java.util.Random;

/**
 * @author XueweiHuang
 * @created 2022-04-10
 */
public class QuickSort3Way implements IMutableSorter {

    private final Random random = new Random();

    /**
     * Time complexity: O(nlogn)
     */
    @Override
    public void sort(int[] arr) {
        quickSort3Way(arr, 0, arr.length - 1);
    }

    private void quickSort3Way(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // Generate a random index between [l, r]
        int p = l + random.nextInt(r - l + 1);
        SortHelper.swap(arr, l, p);
        // Loop invariant: arr[l + 1, lt] < v & arr[lt + 1, i - 1] = v & arr[gt, r] > v
        int lt = l;
        int i = l + 1;
        int gt = r + 1;
        while (i < gt) {
            if (arr[i] < arr[l]) {
                lt++;
                SortHelper.swap(arr, i, lt);
                i++;
            } else if (arr[i] > arr[l]) {
                gt--;
                SortHelper.swap(arr, i, gt);
            } else {
                i++;
            }
        }
        SortHelper.swap(arr, l, lt);
        // arr[l, lt - 1] < v & arr[lt, gt - 1] = v & arr[gt, r] > v
        quickSort3Way(arr, l, lt - 1);
        quickSort3Way(arr, gt, r);
    }
}

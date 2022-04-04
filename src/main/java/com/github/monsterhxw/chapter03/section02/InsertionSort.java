package com.github.monsterhxw.chapter03.section02;

/**
 * @author XueweiHuang
 * @created 2022-04-02
 */
public class InsertionSort implements IMutableSorter {

    /**
     * Time complexity: O(n^2)
     */
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 将 A[i] 插入到合适的位置
            int temp = arr[i];
            int j = i;
            while (j - 1 >= 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}

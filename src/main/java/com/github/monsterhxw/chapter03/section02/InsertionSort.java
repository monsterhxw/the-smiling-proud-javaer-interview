package com.github.monsterhxw.chapter03.section02;

import static com.github.monsterhxw.chapter03.section02.SortHelper.swap;

/**
 * @author XueweiHuang
 * @created 2022-04-02
 */
public class InsertionSort implements IMutableSorter {

    @Override
    public void sort(int[] A) {
        for (int i = 0; i < A.length; i++) {
            // 将 A[i] 插入到合适的位置
            int temp = A[i];
            int j = i;
            while (j - 1 >= 0 && temp < A[j - 1]) {
                A[j] = A[j - 1];
                j--;
            }
            A[j] = temp;
        }
    }
}

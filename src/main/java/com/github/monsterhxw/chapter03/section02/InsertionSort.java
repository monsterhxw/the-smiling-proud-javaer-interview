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
            for (int j = i; j - 1 >= 0; j--) {
                if (A[j] < A[j - 1]) {
                    swap(A, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}

package com.github.monsterhxw.chapter03.section02;

/**
 * @author XueweiHuang
 * @created 2022-04-02
 */
public class SortHelper {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

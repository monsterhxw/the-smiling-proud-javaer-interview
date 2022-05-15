package com.github.monsterhxw.external.heap;

/**
 * @author Xuewei Huang
 * @created 2022-05-15
 */
public class HeapSort {

    public static <T extends Comparable<T>> void sort(T[] data) {
        // build max heap
        // Time complexity: O(n)
        heapify(data, data.length);
        // swap and sift down root
        // Time complexity: O(nlogn)
        for (int i = data.length - 1; i >= 0; i--) {
            swap(data, 0, i);
            siftDown(data, i, 0);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] data, int size) {
        int lastNonLeafNodeIdx = parent(size - 1);
        for (int i = lastNonLeafNodeIdx; i >= 0; i--) {
            siftDown(data, size, i);
        }
    }

    private static <T extends Comparable<T>> void siftDown(T[] data, int size, int k) {
        while (leftChild(k) < size) {
            int j = leftChild(k);
            if (j + 1 < size && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(data, k, j);
            k = j;
        }
    }

    private static int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index must be greater than 0.");
        }
        return (index - 1) / 2;
    }

    private static int leftChild(int index) {
        return 2 * index + 1;
    }

    private static <T extends Comparable<T>> void swap(T[] data, int i, int j) {
        int size = data.length;
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
package com.github.monsterhxw.external.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuewei Huang
 * @created 2022-05-12
 */
public class BinaryMaxHeap<E extends Comparable<E>> {

    private List<E> data;

    public BinaryMaxHeap() {
        this(10);
    }

    public BinaryMaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public BinaryMaxHeap(E[] arr) {
        this(arr.length);
        heapify(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            data.add(arr[i]);
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && greaterThan(k, parent(k))) {
            swap(data, k, parent(k));
            k = parent(k);
        }
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not find max data when heap is empty.");
        }
        return data.get(0);
    }

    public E extractMax() {
        E max = findMax();
        swap(data, 0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);
        return max;
    }

    public E replace(E e) {
        E max = findMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }


    private void siftDown(int k) {
        while (leftChild(k) < data.size()) {
            int j = leftChild(k);
            if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            swap(data, k, j);
            k = j;
        }
    }

    private int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index must be greater than 0.");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private boolean greaterThan(int index1, int index2) {
        return data.get(index1).compareTo(data.get(index2)) > 0;
    }

    private void swap(List<E> data, int i, int j) {
        int size = data.size();
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    private void heapify(E[] arr, int size) {
        // 找到最后一个非叶子节点索引
        int lastNonLeafNodeIdx = parent(size - 1);
        for (int i = lastNonLeafNodeIdx; i >= 0; i--) {
            siftDown(arr, i, size);
        }
    }

    private void siftDown(E[] arr, int k, int size) {
        while (leftChild(k) < size) {
            int j = leftChild(k);
            if (j + 1 < size && arr[j + 1].compareTo(arr[j]) > 0) {
                j++;
            }
            if (arr[k].compareTo(arr[j]) >= 0) {
                break;
            } else {
                swap(arr, k, j);
                k = j;
            }
        }
    }

    private void swap(E[] arr, int i, int j) {
        int size = arr.length;
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

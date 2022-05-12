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
}

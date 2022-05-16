package com.github.monsterhxw.external.segmenttree;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * @author Xuewei Huang
 * @created 2022-05-16
 */
public class SegmentTree<E> {

    private E[] data;

    private E[] tree;

    private BiFunction<E, E, E> merger;

    public SegmentTree(E[] arr, BiFunction<E, E, E> merger) {
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        merger = merger;
        buildSegmentTree(0, 0, data.length - 1, merger);
    }

    /**
     * 在 treeIndex 的位置创建表示区间 [l, r] 的线段树
     */
    private void buildSegmentTree(int treeIndex, int left, int right, BiFunction<E, E, E> merger) {
        if (left == right) {
            tree[treeIndex] = data[left];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = left + (right - left) / 2;
        buildSegmentTree(leftTreeIndex, left, mid, merger);
        buildSegmentTree(rightTreeIndex, mid + 1, right, merger);
        tree[treeIndex] = merger.apply(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(tree);
    }
}

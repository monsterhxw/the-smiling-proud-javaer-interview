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
        buildSegmentTree(tree, 0, data, 0, data.length - 1, merger);
    }

    /**
     * 在 treeIndex 的位置创建表示区间 [l, r] 的线段树
     */
    private void buildSegmentTree(E[] tree, int treeIndex, E[] data, int left, int right, BiFunction<E, E, E> merger) {
        if (left == right) {
            tree[treeIndex] = data[left];
            return;
        }

        int mid = left + (right - left) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        buildSegmentTree(tree, leftTreeIndex, data, left, mid, merger);

        int rightTreeIndex = rightChild(treeIndex);
        buildSegmentTree(tree, rightTreeIndex, data, mid + 1, right, merger);

        tree[treeIndex] = merger.apply(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回区间 [queryL, queryR] 的值
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int left, int right, int queryL, int queryR) {
        if (queryL == left && queryR == right) {
            return tree[treeIndex];
        }
        int mid = left + (right - left) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL > mid) {
            return query(rightTreeIndex, mid + 1, right, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, left, mid, queryL, queryR);
        } else {
            E leftResult = query(leftTreeIndex, left, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, right, mid + 1, queryR);
            return merger.apply(leftResult, rightResult);
        }
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

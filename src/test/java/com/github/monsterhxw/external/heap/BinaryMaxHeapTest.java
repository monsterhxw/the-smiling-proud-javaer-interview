package com.github.monsterhxw.external.heap;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-12
 */
class BinaryMaxHeapTest {

    @Test
    void testAddAndExtractMax() {
        int n = 1_000_000;
        assertDoesNotThrow(() -> testAddAndExtractMax(n));
    }

    void testAddAndExtractMax(int n) {
        BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        testMaxHeap(maxHeap, n);
    }

    @Test
    void testBinaryMaxHeapByArray() {
        Integer[] arr = new Integer[]{15, 5, 3, 17, 10, 84, 19, 6, 22, 9};
        BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<>(arr);
        assertEquals(84, maxHeap.findMax());
        assertDoesNotThrow(() -> testMaxHeap(maxHeap, arr.length));
        assertEquals(0, maxHeap.size());
    }

    <E extends Comparable<E>> void testMaxHeap(BinaryMaxHeap<E> maxHeap, int n) {
        E[] arr = (E[]) new Comparable[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if (arr[i - 1].compareTo(arr[i]) < 0) {
                throw new IllegalArgumentException("Error");
            }
        }
    }
}
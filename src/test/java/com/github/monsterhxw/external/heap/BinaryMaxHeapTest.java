package com.github.monsterhxw.external.heap;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
    }
}
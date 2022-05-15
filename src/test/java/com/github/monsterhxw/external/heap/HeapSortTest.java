package com.github.monsterhxw.external.heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-15
 */
class HeapSortTest {

    @Test
    void sort() {
        Integer[] nums = generateIntegerArray(10_000);
        System.out.println("Before sort: " + Arrays.toString(nums));
        HeapSort.sort(nums);
        assertDoesNotThrow(() -> testSortByOrder(nums));
        System.out.println("After sort: " + Arrays.toString(nums));
    }

    Integer[] generateIntegerArray(int size) {
        Integer[] nums = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(size);
        }
        return nums;
    }

    <T extends Comparable<T>> void testSortByOrder(T[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i].compareTo(data[i - 1]) < 0) {
                throw new IllegalArgumentException("Error.");
            }
        }
    }
}
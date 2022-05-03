package com.github.monsterhxw.external.binarysearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-03
 */
class BinarySearchRecursiveTest {

    private int[] data;

    @BeforeEach
    void setUp() {
        data = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    }

    @Test
    void search() {
        System.out.println("data is: " + Arrays.toString(data));
        search(5);
        search(8);
        search(9);
        search(-2);
    }

    private void search(int target) {
        int expected = findTargetByIterative(data, target);
        int actual = BinarySearchRecursive.search(data, target);
        System.out.printf("target is: %d, expected index is: %d, actual index is: %d.\n", target, expected, actual);
        assertEquals(expected, actual);
    }

    private int findTargetByIterative(int[] data, int target) {
        int res = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return res;
    }
}
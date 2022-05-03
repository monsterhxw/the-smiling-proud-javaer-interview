package com.github.monsterhxw.external.binarysearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-03
 */
class BinarySearchLowerTest {

    private final int[] nums = new int[]{1, 1, 3, 3, 5, 5};

    @Test
    void lower() {
        assertEquals(-1, BinarySearchLower.lower(nums, 0));
        assertEquals(-1, BinarySearchLower.lower(nums, 1));
        assertEquals(1, BinarySearchLower.lower(nums, 2));
        assertEquals(1, BinarySearchLower.lower(nums, 3));
        assertEquals(3, BinarySearchLower.lower(nums, 4));
        assertEquals(3, BinarySearchLower.lower(nums, 5));
        assertEquals(5, BinarySearchLower.lower(nums, 6));
    }
}
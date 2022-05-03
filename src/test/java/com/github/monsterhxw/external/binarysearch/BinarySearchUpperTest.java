package com.github.monsterhxw.external.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Xuewei Huang
 * @created 2022-05-03
 */
class BinarySearchUpperTest {

    private final int[] nums = new int[]{1, 1, 3, 3, 5, 5};

    @Test
    void upper() {
        assertEquals(0, BinarySearchUpper.upper(nums, 0));
        assertEquals(2, BinarySearchUpper.upper(nums, 1));
        assertEquals(2, BinarySearchUpper.upper(nums, 2));
        assertEquals(4, BinarySearchUpper.upper(nums, 3));
        assertEquals(4, BinarySearchUpper.upper(nums, 4));
        assertEquals(6, BinarySearchUpper.upper(nums, 5));
        assertEquals(6, BinarySearchUpper.upper(nums, 6));
    }
}
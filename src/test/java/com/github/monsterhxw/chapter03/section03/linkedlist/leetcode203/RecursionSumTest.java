package com.github.monsterhxw.chapter03.section03.linkedlist.leetcode203;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-04-19
 */
class RecursionSumTest {

    private int[] nums;

    private int sum;

    @BeforeEach
    void setUp() {
        nums = new int[]{1, 2, 3, 4, 5};
        sum = 0;
        for (int num : nums) {
            sum += num;
        }
    }

    @Test
    void sum() {
        int result = RecursionSum.sum(nums);
        assertEquals(sum, result);
        System.out.println("Recursion sum result is " + result);
    }
}
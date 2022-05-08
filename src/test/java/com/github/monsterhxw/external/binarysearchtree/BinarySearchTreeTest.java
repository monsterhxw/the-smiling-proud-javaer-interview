package com.github.monsterhxw.external.binarysearchtree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-07
 */
class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    private int[] nums;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
        nums = new int[]{5, 3, 6, 8, 4, 2};
        System.out.println("nums is: " + Arrays.toString(nums));
    }

    @Test
    void size() {
        assertEquals(0, bst.size());
    }

    @Test
    void isEmpty() {
        assertTrue(bst.isEmpty());
    }

    @Test
    void add() {
        for (int num : nums) {
            assertTrue(bst.contains(num));
        }
    }

    @Test
    void contains() {
        add();
        for (int num : nums) {
            assertTrue(bst.contains(num));
        }
    }

    @Test
    void add2() {
        for (int num : nums) {
            bst.add2(num);
        }
        for (int num : nums) {
            assertTrue(bst.contains(num));
        }
    }

    @Test
    void preOrder() {
        for (int num : nums) {
            bst.add(num);
        }
        bst.preOrder();
    }

    @Test
    void inOrder() {
        for (int num : nums) {
            bst.add(num);
        }
        bst.inOrder();
    }

    @Test
    void postOrder() {
        for (int num : nums) {
            bst.add(num);
        }
        bst.postOrder();
    }

    @Test
    void preOrderNotRecursive() {
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println("recursive");
        bst.preOrder();
        System.out.println("not recursive");
        bst.preOrderNotRecursive();
    }

    @Test
    void toStringTest() {
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst.toString());
    }
}
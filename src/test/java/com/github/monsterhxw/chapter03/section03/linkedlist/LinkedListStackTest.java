package com.github.monsterhxw.chapter03.section03.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-04-17
 */
class LinkedListStackTest {

    private LinkedListStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
    }

    @AfterEach
    void tearDown() {
        System.out.println();
    }

    @Test
    void push() {
        stack.push(2);
        assertEquals(6, stack.size());
    }

    @Test
    void pop() {
        var pop = stack.pop();
        assertEquals(4, pop);
        assertEquals(4, stack.size());
    }

    @Test
    void peek() {
        var pop = stack.pop();
        assertEquals(4, pop);
        assertEquals(4, stack.size());
    }

    @Test
    void size() {
        assertEquals(5, stack.size());
    }

    @Test
    void isEmpty() {
        assertFalse(stack.isEmpty());
        for (int i = 0; i < 5; i++) {
            stack.pop();
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    void toStr() {
        System.out.println(stack);
    }
}
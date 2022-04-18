package com.github.monsterhxw.chapter03.section03.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-04-17
 */
class LinkedListQueueTest {

    private LinkedListQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedListQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
    }

    @AfterEach
    void tearDown() {
        System.out.println();
    }

    @Test
    void size() {
        System.out.println("Test size method:");
        assertEquals(5, queue.size());
    }

    @Test
    void isEmpty() {
        System.out.println("Test isEmpty method:");
        assertFalse(queue.isEmpty());
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
        }
        assertTrue(queue.isEmpty());
    }

    @Test
    void enqueue() {
        System.out.println("Test enqueue method:");
        queue.enqueue(666);
        assertEquals(6, queue.size());
        System.out.println(queue);
    }

    @Test
    void dequeue() {
        System.out.println("Test dequeue method:");
        var dequeue = queue.dequeue();
        assertEquals(4, queue.size());
        assertEquals(0, dequeue);
        System.out.println(queue);
    }

    @Test
    void peek() {
        System.out.println("Test peek method:");
        var peek = queue.peek();
        assertEquals(0, peek);
        assertEquals(5, queue.size());
    }

    @Test
    void testToString() {
        System.out.println("Test toString method:");
        System.out.println(queue);
    }
}
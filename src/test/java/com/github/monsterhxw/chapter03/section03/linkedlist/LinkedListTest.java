package com.github.monsterhxw.chapter03.section03.linkedlist;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-04-17
 */
class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @BeforeEach
    public void beforeAll() {
        linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
    }

    @AfterEach
    public void println() {
        System.out.println();
    }

    @Test
    public void test() {
        linkedList.add(2, 666);
        System.out.println(linkedList);
        assertEquals(6, linkedList.size());
    }

    @Test
    public void testRemove() {
        var remove = linkedList.remove(2);
        assertEquals(4, linkedList.size());
        assertEquals(2, remove);
        System.out.println(linkedList);
    }

    @Test
    public void testRemoveFirst() {
        var removeFirst = linkedList.removeFirst();
        assertEquals(4, linkedList.size());
        assertEquals(4, removeFirst);
        System.out.println(linkedList);
    }

    @Test
    public void testRemoveLast() {
        var removeLast = linkedList.removeLast();
        assertEquals(4, linkedList.size());
        assertEquals(0, removeLast);
        System.out.println(linkedList);
    }
}
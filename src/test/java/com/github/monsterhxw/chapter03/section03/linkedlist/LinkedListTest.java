package com.github.monsterhxw.chapter03.section03.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-04-17
 */
class LinkedListTest {

    @Test
    public void test() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);
        assertEquals(6, linkedList.size());
    }
}
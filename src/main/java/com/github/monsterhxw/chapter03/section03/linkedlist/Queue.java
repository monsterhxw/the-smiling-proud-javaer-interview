package com.github.monsterhxw.chapter03.section03.linkedlist;

/**
 * @author Xuewei Huang
 * @created 2022-04-17
 */
public interface Queue<E> {

    int size();

    boolean isEmpty();

    void enqueue(E data);

    E dequeue();

    E peek();
}

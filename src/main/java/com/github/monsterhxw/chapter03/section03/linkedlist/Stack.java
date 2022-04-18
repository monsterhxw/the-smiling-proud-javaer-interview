package com.github.monsterhxw.chapter03.section03.linkedlist;

/**
 * @author Xuewei Huang
 * @created 2022-04-17
 */
public interface Stack<E> {

    void push(E e);

    E pop();

    E peek();

    int size();

    boolean isEmpty();
}

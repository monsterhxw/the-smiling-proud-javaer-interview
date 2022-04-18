package com.github.monsterhxw.chapter03.section03.linkedlist;

/**
 * @author Xuewei Huang
 * @created 2022-04-17
 */
public class LinkedListStack<E> implements Stack<E> {

    private final LinkedList<E> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top ").append(list);
        return sb.toString();
    }
}

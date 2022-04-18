package com.github.monsterhxw.chapter03.section03.linkedlist;

import java.util.Objects;

/**
 * @author Xuewei Huang
 * @created 2022-04-17
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node head;

    private Node tail;

    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void enqueue(E data) {
        var node = new Node(data, null);
        if (this.tail == null) {
            this.head = this.tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        var dequeueNode = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        dequeueNode.next = null;
        size--;
        return dequeueNode.data;
    }

    @Override
    public E peek() {
        if (head == null) {
            throw new IllegalArgumentException("Peek failed. Queue is empty.");
        }
        return head.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front ");
        Node cur = head;
        while (cur != null) {
            sb.append(cur).append(" -> ");
            cur = cur.next;
        }
        sb.append(" null tail ");
        return sb.toString();
    }

    private class Node {
        private E data;
        private Node next;

        private Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        private Node(E data) {
            this(data, null);
        }

        private Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return Objects.isNull(data) ? "null" : data.toString();
        }
    }
}

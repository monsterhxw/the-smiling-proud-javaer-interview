package com.github.monsterhxw.chapter03.section03.linkedlist;

/**
 * @author Xuewei Huang
 * @created 2022-04-20
 */
public class LinkedListRecursion<E> {

    private int size;

    private Node head;

    public LinkedListRecursion() {
        this.size = 0;
        this.head = null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        head = add(head, index, e);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        return get(head, index);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Update failed. Illegal index.");
        }
        set(head, index, e);
    }

    public boolean contains(E e) {
        return contains(head, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        Pair<Node, E> res = remove(head, index);
        size--;
        head = res.getKey();
        return res.getValue();
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    private Node removeElement(Node node, E e) {
        if (node == null) {
            return null;
        }
        node.next = removeElement(node.next, e);
        if (node.data.equals(e)) {
            size--;
            return node.next;
        } else {
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            res.append(cur + " -> ");
            cur = cur.next;
        }
        res.append("null");
        return res.toString();
    }

    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) {
            return new Pair<>(node.next, node.data);
        }
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        } else if (node.data.equals(e)) {
            return true;
        } else {
            return contains(node.next, e);
        }
    }

    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.data = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    private E get(Node node, int index) {
        if (index == 0) {
            return node.data;
        }
        return get(node.next, index - 1);
    }

    private Node add(Node node, int index, E e) {
        if (index == 0) {
            return new Node(e, node);
        }
        node.next = add(node, index - 1, e);
        return node;
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
    }

    private class Pair<N, E> {
        private N key;
        private E value;

        private Pair(N key, E value) {
            this.key = key;
            this.value = value;
        }

        public N getKey() {
            return key;
        }

        public E getValue() {
            return value;
        }
    }
}

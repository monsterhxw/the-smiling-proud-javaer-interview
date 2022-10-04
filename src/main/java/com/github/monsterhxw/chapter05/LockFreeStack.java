package com.github.monsterhxw.chapter05;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Xuewei Huang
 * @since 2022-10-05
 */
public class LockFreeStack<V> {
    
    private static class Node<V> {
        
        Node<V> next;
        
        private V value;
        
        Node(V value) {
            this.value = value;
            this.next = null;
        }
    }
    
    private final AtomicStampedReference<Node<V>> head;
    
    public LockFreeStack() {
        Node<V> headNode = new Node<>(null);
        this.head = new AtomicStampedReference<>(headNode, 0);
    }
    
    public void push(V value) {
        Node<V> newNode = new Node<>(value);
        while (true) {
            int stamp = head.getStamp();
            Node<V> headNode = this.head.getReference();
            newNode.next = headNode;
            if (head.compareAndSet(headNode, newNode, stamp, stamp + 1)) {
                return;
            }
        }
    }
    
    public V pop() {
        while (true) {
            int stamp = head.getStamp();
            Node<V> headNode = head.getReference();
            if (headNode == null) {
                return null;
            }
            Node<V> next = headNode.next;
            if (head.compareAndSet(headNode, next, stamp, stamp + 1)) {
                return headNode.value;
            }
        }
    }
}

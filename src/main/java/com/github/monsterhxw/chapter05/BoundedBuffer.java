package com.github.monsterhxw.chapter05;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Xuewei Huang
 * @since 2022-10-04
 */
public class BoundedBuffer<V> {
    
    private final Lock lock = new ReentrantLock();
    
    private final Condition notFull = lock.newCondition();
    
    private final Condition notEmpty = lock.newCondition();
    
    private final LinkedList<V> items = new LinkedList<>();
    
    private final int count;
    
    public BoundedBuffer() {
        this(2);
    }
    
    public BoundedBuffer(int count) {
        this.count = count;
    }
    
    public void put(V v) {
        lock.lock();
        try {
            // 队列满了
            while (items.size() >= count) {
                // 阻塞写线程
                notFull.await();
            }
            // 入队列
            items.addLast(v);
            // 唤醒读线程
            notEmpty.signal();
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }
    
    public V take() {
        lock.lock();
        try {
            // 队列空
            while (items.size() == 0) {
                // 阻塞读线程
                notEmpty.await();
            }
            V v = items.removeFirst();
            // 唤醒写线程
            notFull.signal();
            return v;
        } catch (InterruptedException ignore) {
            return null;
        } finally {
            lock.unlock();
        }
    }
}

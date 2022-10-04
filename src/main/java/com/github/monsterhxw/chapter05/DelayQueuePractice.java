package com.github.monsterhxw.chapter05;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Xuewei Huang
 * @since 2022-10-04
 */
public class DelayQueuePractice {
    
    private static class DelayedItem<T> implements Delayed {
        
        private T value;
        
        private long time = 0;
        
        public DelayedItem(T value, long delay) {
            this.value = value;
            this.time = delay + System.currentTimeMillis();
        }
        
        @Override
        public long getDelay(TimeUnit unit) {
            return this.time - System.currentTimeMillis();
        }
        
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.time - ((DelayedItem<?>) o).time);
        }
        
        public T getValue() {
            return this.value;
        }
        
        @Override
        public String toString() {
            return "DelayedItem{" + "value=" + value + ", time=" + time + '}';
        }
    }
    
    private static final DelayQueue<DelayedItem<Integer>> DELAY_QUEUE = new DelayQueue<>();
    
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                DELAY_QUEUE.offer(new DelayedItem<>(i, i * 1000));
            }
        }).start();
        
        new Thread(() -> {
            while (true) {
                try {
                    var item = DELAY_QUEUE.take();
                    System.out.println(item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

package com.github.monsterhxw.chapter05;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @since 2022-10-04
 */
class BlockingQueuePracticeTest {
    
    @Test
    void arrayBlocking() {
        int capacity = 10;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity);
        
        // producer
        for (int i = 0; i < 100; i++) {
            new Thread(() -> queue.offer((int) (Math.random() * 1000))).start();
        }
        
        // Consumer
        for (int i = 0; i < capacity; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println("Receive: " + queue.poll());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
package com.github.monsterhxw.chapter05;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @since 2022-10-05
 */
class LockFreeStackTest {
    
    @Test
    void singleThread() {
        var stack = new LockFreeStack<Integer>();
        
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        
        Integer j = null;
        Integer i = 99;
        while ((j = stack.pop()) != null) {
            assertEquals(j + "", i-- + "");
        }
    }
    
    @Test
    void multiThread() throws InterruptedException {
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        
        for (int i = 0; i < 16; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    stack.push(j);
                }
            });
            thread.start();
            thread.join();
        }
        
        Integer c = 0;
        while (stack.pop() != null) {
            c++;
        }
        
        assertEquals(c + "", "1600");
    }
    
    @Test
    void multiThread2() throws InterruptedException {
        var stack = new LockFreeStack<Integer>();
        var list = IntStream.range(0, 16).mapToObj(i -> {
            var t = new Thread(() -> {
                System.out.println(Thread.currentThread().getId());
                for (int j = 0; j < 100; j++) {
                    try {
                        stack.push(j);
                        Thread.sleep(1);
                        stack.push(j);
                        Thread.sleep(1);
                        stack.pop();
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            return t;
        }).collect(Collectors.toList());
        list.forEach(t -> {
            System.out.println("wait join..");
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Integer c = 0;
        while (stack.pop() != null) {
            c++;
        }
        assertEquals(c + "", "1600");
    }
}
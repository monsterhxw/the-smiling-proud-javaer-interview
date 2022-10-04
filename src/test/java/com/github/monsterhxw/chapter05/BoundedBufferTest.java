package com.github.monsterhxw.chapter05;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuewei Huang
 * @since 2022-10-04
 */
class BoundedBufferTest {
    
    @Test
    void test() {
        BoundedBuffer<String> buffer = new BoundedBuffer<>();
        
        int count = 10;
        
        for (int i = 0; i < count; i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName() + " take: " + buffer.take())).start();
        }
    
        for (int i = 0; i < count; i++) {
            new Thread(() -> buffer.put(String.valueOf(Thread.currentThread().getName()))).start();
        }
    }
}
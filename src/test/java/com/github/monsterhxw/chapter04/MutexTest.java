package com.github.monsterhxw.chapter04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-08-09
 */
class MutexTest {
    
    @Test
    public void waitAwait() {
        var obj = new Object();
        
        var t1 = new Thread(() -> {
            System.out.println("before-wait...1");
            // wait
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after-wait...1");
        });
        
        var t2 = new Thread(() -> {
            System.out.println("before-wait...2");
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after-wait...2");
        });
        
        t1.start();
        t2.start();
    }
}
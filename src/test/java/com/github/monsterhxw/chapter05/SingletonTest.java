package com.github.monsterhxw.chapter05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @since 2022-10-04
 */
class SingletonTest {
    
    @Test
    void test() {
        for (int i = 0; i < 200; i++) {
            new Thread(Singleton::getInstance).start();
        }
    }
}
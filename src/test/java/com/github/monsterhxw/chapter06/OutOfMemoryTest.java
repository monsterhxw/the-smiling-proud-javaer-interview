package com.github.monsterhxw.chapter06;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @since 2022-10-06
 */
class OutOfMemoryTest {
    
    @Test
    void testOutOfMemory() throws InterruptedException {
        List<int[]> list = new ArrayList<>();
        
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            // 4Byte * 1024 * 256  = 4KB * 256 = 1MB
            list.add(new int[1024 * 256]);
        }
    }
}
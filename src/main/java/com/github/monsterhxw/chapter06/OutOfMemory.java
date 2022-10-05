package com.github.monsterhxw.chapter06;

/**
 * @author Xuewei Huang
 * @since 2022-10-06
 */
public class OutOfMemory {
    
    /**
     * VM options: -verbose:gc -XX:+PrintGCDetails -Xmx20m
     */
    public static void main(String[] args) throws InterruptedException {
        
        // 4Byte * 1024 * 1024 = 4MB
        int[] a = new int[1024 * 1024];
        // 4Byte * 1024 * 1024 = 4MB
        int[] b = new int[1024 * 1024];
        // 4Byte * 1024 * 1024 = 4MB
        int[] c = new int[1024 * 1024 * 3];
        
        a = null;
        b = null;
        c = null;
        
        Thread.sleep(1000);
        
        // 4Byte * 1024 * 1024 = 4MB
        int[] d = new int[1024 * 1024];
        
        // if you want OOM
        // 4Byte * 1024 * 1024 = 4MB
        int[] e = new int[1024 * 1024];
        
        System.gc();
    }
}

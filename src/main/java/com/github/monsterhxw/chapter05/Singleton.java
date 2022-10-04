package com.github.monsterhxw.chapter05;

/**
 * @author Xuewei Huang
 * @since 2022-10-04
 */
public class Singleton {
    
    private static volatile Singleton INSTANCE;
    
    private Singleton() {
    }
    
    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                System.out.print("Synchronized block pass thread: " + Thread.currentThread().getName());
                System.out.println(", Instance = " + INSTANCE);
                if (INSTANCE == null) {
                    // new 不是一个原子操作 ,指令可能会重排序
                    // 1. 先分配内存，让 instance 指向这块内存，然后执行对象的初始化，
                    //    这种会有问题，在初始化执行没有完成，这时候 cpu 切换时间片，
                    //    导致其他线程执行 标记1 时判断不为空，直接返回，但是对象还没有初始化完
                    // 2. 先分配内存，然后对象初始化，让 instance 指向这个内存
                    // 总结 ： volatile 修饰变量，指令不会重排序 ，也就是 上面的步骤2
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}

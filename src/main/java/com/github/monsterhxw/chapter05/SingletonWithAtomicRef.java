package com.github.monsterhxw.chapter05;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Xuewei Huang
 * @since 2022-10-04
 */
public class SingletonWithAtomicRef {
    
    private static final AtomicReference<SingletonWithAtomicRef> REF = new AtomicReference<>();
    
    private SingletonWithAtomicRef() {
    }
    
    public static SingletonWithAtomicRef getInstance() {
        if (REF.getAcquire() == null) {
            synchronized (Singleton.class) {
                System.out.println("Synchronized block pass thread: " + Thread.currentThread().getName());
                if (REF.getAcquire() == null) {
                    REF.setRelease(new SingletonWithAtomicRef());
                }
            }
        }
        return REF.getAcquire();
    }
}

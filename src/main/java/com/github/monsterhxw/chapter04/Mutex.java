package com.github.monsterhxw.chapter04;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Xuewei Huang
 * @created 2022-08-09
 */
public class Mutex {
    
    static class Sync extends AbstractQueuedSynchronizer {
        
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }
        
        @Override
        protected boolean tryRelease(int arg) {
            return compareAndSetState(1, 0);
        }
    }
    
    private final Sync sync = new Sync();
    
    private void lock() {
        sync.acquire(0);
    }
    
    private void unlock() {
        sync.release(0);
    }
}

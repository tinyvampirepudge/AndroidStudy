package com.tinytongtong.androidstudy.java.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2020/8/25 3:45 PM
 * @Version TODO
 */
public class ReentrantLockTest {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock();
        try {
            a++;
        } finally {
            lock.unlock();
        }
    }

    public void reader() {
        lock.lock();
        try {
            int i = a;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.writer();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.reader();
            }
        }).start();
    }
}

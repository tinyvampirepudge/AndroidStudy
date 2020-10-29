package com.tinytongtong.androidstudy.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Description: WeakReference和引用队列测试
 * @Author tinytongtong
 * @Date 2020/10/28 11:28 AM
 * @Version
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o, referenceQueue);

        System.out.println(o);
        System.out.println(referenceQueue);

        System.gc();

        System.out.println(o);
        System.out.println(referenceQueue);
    }
}

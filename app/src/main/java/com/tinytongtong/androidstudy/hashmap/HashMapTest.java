package com.tinytongtong.androidstudy.hashmap;

import java.util.HashMap;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2020/9/1 7:46 PM
 * @Version
 */
public class HashMapTest {
    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        int size = 0;
        int result = tableSizeFor(size);
        System.out.println(result);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

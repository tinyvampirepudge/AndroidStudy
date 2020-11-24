package com.tinytongtong.androidstudy.java.singleton;

/**
 * @Description: 单例-静态内部类。无法防反射
 * @Author tinytongtong
 * @Date 2020/11/24 9:33 AM
 * @Version
 */
public class SingletonTest {
    private SingletonTest() {

    }

    private static final class SingletonTestHolder {
        private static final SingletonTest INSTANCE = new SingletonTest();
    }

    public static SingletonTest getInstance() {
        return SingletonTestHolder.INSTANCE;
    }
}

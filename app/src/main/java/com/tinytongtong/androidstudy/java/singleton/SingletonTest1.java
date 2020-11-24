package com.tinytongtong.androidstudy.java.singleton;

/**
 * @Description: 单例-DCL：无法防反射
 *
 * @Author tinytongtong
 * @Date 2020/11/24 9:36 AM
 * @Version
 */
public class SingletonTest1 {
    private volatile static SingletonTest1 instance;

    private SingletonTest1() {
    }

    public static SingletonTest1 getInstance() {
        if (instance == null) {
            synchronized (SingletonTest1.class) {
                if (instance == null) {
                    instance = new SingletonTest1();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonTest1 singletonTest1 = SingletonTest1.getInstance();
        SingletonTest1 singletonTest11 = SingletonTest1.getInstance();
        System.out.println(singletonTest1);
        System.out.println(singletonTest11);
        System.out.println(singletonTest1 == singletonTest11);
    }
}

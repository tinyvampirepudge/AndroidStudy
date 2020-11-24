package com.tinytongtong.androidstudy.java.singleton;

/**
 * @Description: 单例-枚举：可以防反射
 * @Author tinytongtong
 * @Date 2020/11/24 9:40 AM
 * @Version
 */
public class SingletonTest2 {
    private SingletonTest2() {

    }

    private enum Singleton {
        INSTANCE;
        private SingletonTest2 instance;

        Singleton() {
            instance = new SingletonTest2();
        }

        private SingletonTest2 getInstance() {
            return instance;
        }
    }

    public static SingletonTest2 getInstance() {
        return Singleton.INSTANCE.instance;
    }

    public static void main(String[] args) {
        SingletonTest2 singletonTest2 = SingletonTest2.getInstance();
        SingletonTest2 singletonTest21 = SingletonTest2.getInstance();
        System.out.println(singletonTest2);
        System.out.println(singletonTest21);
        System.out.println(singletonTest2 == singletonTest21);
    }
}

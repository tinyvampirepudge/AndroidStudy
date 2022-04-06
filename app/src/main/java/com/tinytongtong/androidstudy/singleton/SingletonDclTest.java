package com.tinytongtong.androidstudy.singleton;

/**
 * @Description: 单例-dcl
 * @Author wangjianzhou
 * @Date 4/6/22 11:46 PM
 * @Version TODO
 */
public class SingletonDclTest {
    private volatile static SingletonDclTest instance;

    private SingletonDclTest() {
    }

    public static SingletonDclTest getInstance() {
        if (instance == null) {
            synchronized (SingletonDclTest.class) {
                if (instance == null) {
                    instance = new SingletonDclTest();
                }
            }
        }
        return instance;
    }
}

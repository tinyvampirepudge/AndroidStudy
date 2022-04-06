package com.tinytongtong.androidstudy.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 单例 防反射
 * @Author wangjianzhou
 * @Date 4/6/22 11:40 PM
 * @Version TODO
 */
public class SingletonTest {
    private SingletonTest() {
        if(SingletonTestHolder.INSTANCE != null){
            throw new IllegalArgumentException("单例不允许反射");
        }
    }

    private static final class SingletonTestHolder {
        private static final SingletonTest INSTANCE = new SingletonTest();
    }

    public static SingletonTest getInstance() {
        return SingletonTestHolder.INSTANCE;
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<SingletonTest> declaredConstructor = SingletonTest.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        SingletonTest instance1 = declaredConstructor.newInstance();
        SingletonTest instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(SingletonTest.getInstance());
        System.out.println(SingletonTest.getInstance());
    }
}

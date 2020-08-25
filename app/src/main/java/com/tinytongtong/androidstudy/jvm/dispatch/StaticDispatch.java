package com.tinytongtong.androidstudy.jvm.dispatch;

/**
 * @Description: 静态分派
 * @Author tinytongtong
 * @Date 2020/8/11 9:21 PM
 * @Version
 */
public class StaticDispatch {
    private static abstract class Human {

    }

    private static class Man extends Human {

    }

    private static class Women extends Human {

    }

    public void sayHello(Human human) {
        System.out.println("hello, guy!");
    }

    public void sayHello(Man man) {
        System.out.println("hello, man!");
    }

    public void sayHello(Women women) {
        System.out.println("hello, women!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(women);
        sd.sayHello((Man) man);
        sd.sayHello((Women) women);
    }
}

package com.tinytongtong.androidstudy.jvm.dispatch;

/**
 * @Description: 动态分派
 * @Author tinytongtong
 * @Date 2020/8/11 9:50 PM
 * @Version
 */
public class DynamicDispatch {
    private static abstract class Human {
        protected abstract void sayHello();
    }

    private static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    private static class Women extends Human {

        @Override
        protected void sayHello() {
            System.out.println("women say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        man.sayHello();
        women.sayHello();

        man = new Women();
        man.sayHello();
    }

}

package com.tinytongtong.androidstudy.test;

/**
 * @Description: 类型转换异常校验
 * @Author tinytongtong
 * @Date 3/27/21 12:28 PM
 */
public class CastExceptionTest {
    public static void main(String[] args) {
        Person person = new Man();
        person.name = "person";
        person = null;
        Man man = (Man) person;// 不会报错
        System.out.println(man);// 这里会打印出 null
    }

    public static class Person {
        public String name;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class Man extends Person {
        public int age;

        @Override
        public String toString() {
            return "Man{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

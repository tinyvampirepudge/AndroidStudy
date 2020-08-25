package com.tinytongtong.androidstudy.jvm.dispatch;

import java.io.Serializable;

/**
 * @Description: 重载方法匹配优先级
 * 存在多个重载版本时，选择“相对更合适的”版本：基本数据类型（char>int>long>float>double），包装类，接口，父类，变长参数（char>int>long>float>double）。
 * @Author wangjianzhou
 * @Date 2020/8/14 12:01 PM
 * @Version
 */
public class Overload {

    public static void sayHello(char arg){
        System.out.println("hello char");
    }

    public static void sayHello(int arg){
        System.out.println("hello int");
    }

    public static void sayHello(long arg){
        System.out.println("hello long");
    }

    public static void sayHello(float arg){
        System.out.println("hello float");
    }

    public static void sayHello(double arg){
        System.out.println("hello double");
    }

    public static void sayHello(Character arg){
        System.out.println("hello Character");
    }

    public static void sayHello(Serializable arg){
        System.out.println("hello Serializable");
    }

    public static void sayHello(Comparable<Character> arg){
        System.out.println("hello Comparable<Character>");
    }

    public static void sayHello(Object arg){
        System.out.println("hello Object");
    }

    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }

    public static void sayHello(int... arg){
        System.out.println("hello int...");
    }

    public static void sayHello(long... arg){
        System.out.println("hello long...");
    }

    public static void sayHello(float... arg){
        System.out.println("hello float...");
    }

    public static void sayHello(double... arg){
        System.out.println("hello double...");
    }

    public static void main(String[] args) {
        Overload.sayHello('c');
    }
}

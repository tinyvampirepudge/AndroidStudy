package com.tinytongtong.androidstudy.kotlin.companion;

/**
 * @Description: Java调用伴生对象
 * @Author tinytongtong
 * @Date 2020/11/11 1:33 PM
 * @Version
 */
public class NumberTestJava {
    public static void main(String[] args) {
        System.out.println(NumberTest.flag);
        System.out.println(NumberTest.plus(3, 4));
    }
}

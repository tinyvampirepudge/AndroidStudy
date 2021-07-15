package com.tinytongtong.androidstudy.test;

/**
 * @Description: Math测试类
 *
 * @Author wangjianzhou
 * @Date 2021/7/13 5:07 PM
 * @Version
 */
public class MathTest {
    public static void main(String[] args) {
        int second = 60;
        int minute = Math.round(second / 60f);
        System.out.println(String.format("minute:%s", minute));
    }
}

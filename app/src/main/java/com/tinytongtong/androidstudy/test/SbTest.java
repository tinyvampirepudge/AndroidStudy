package com.tinytongtong.androidstudy.test;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 3/31/21 9:24 PM
 * @Version
 */
public class SbTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String a = null;
        sb.append(1);
        sb.append(a);
        sb.append("阿西吧");
        System.out.println(sb.toString()); // 1null阿西吧
    }
}

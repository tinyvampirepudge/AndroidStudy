package com.tinytongtong.androidstudy.java.cachepool;

/**
 * @Description: String#intern探究
 * @Author tinytongtong
 * @Date 2020/8/7 5:35 PM
 * @Version
 */
public class StringInternTest {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s1 == s2);// false

        String s3 = "def";
        String s4 = "def";
        System.out.println(s3 == s4);// true

        String s5 = "d" + "e" + "f";
        System.out.println(s3 == s5);// true

        String s6 = new String("g") + new String("h") + new String("i");
        String s7 = "ghi";
        System.out.println(s6 == s7);// false

        String s8 = new String("j") + new String("k") + new String("l");
        s8.intern();
        String s9 = "jkl";
        System.out.println(s8 == s9);// true

        String s10 = new String("m") + new String("n") + new String("o");
        String s11 = "mno";
        s10.intern();
        System.out.println(s10 == s11);// false
    }
}

package com.tinytongtong.androidstudy.java.trycatchfinally;

import java.io.IOError;
import java.io.IOException;
import java.util.IllegalFormatException;

/**
 * @Description:
 * @Author wangjianzhou@qding.me
 * @Date 2020/8/14 3:00 PM
 * @Version
 */
public class TryCatchFinallyTest {
    public static int test() throws Exception{
        int a = 0;
        try {
            a = 10;
            System.out.println("in the try block");
//            return a;
            throw new Exception();
        } catch (IOException e) {
            System.out.println("in the catch block");
            a = 20;
            return a;
        } finally {
            System.out.println("in the finally block");
            a = 30;
        }
    }

    public static void main(String[] args) throws Exception {
        int a = test();
        System.out.println("a is:" + a);
    }
}

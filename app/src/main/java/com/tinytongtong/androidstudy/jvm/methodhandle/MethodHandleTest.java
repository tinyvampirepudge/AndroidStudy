package com.tinytongtong.androidstudy.jvm.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Random;

/**
 * @Description: MethodHandle基础用法演示
 * @Author tinytongtong
 * @Date 2020/8/14 9:56 PM
 * @Version
 */
public class MethodHandleTest {
    private static int count = new Random().nextInt();

    static class ClassA {
        public void println(String s) {
            System.out.println("ClassA#println:"+s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = count % 2 == 0 ? System.out : new ClassA();
//        getPrintlnMH(obj).invokeExact("icyfenix");
    }

//    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
//        MethodType mt = MethodType.methodType(void.class, String.class);
//        return MethodHandles.lookup().findVirtual(receiver.getClass(),"println",mt).bindTo(receiver);
//    }
}

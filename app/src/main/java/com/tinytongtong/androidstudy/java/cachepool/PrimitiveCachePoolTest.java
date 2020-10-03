package com.tinytongtong.androidstudy.java.cachepool;

import com.bumptech.glide.load.model.ByteArrayLoader;

/**
 * @Description: 基本类型的缓存池探究
 * @Author tinytongtong
 * @Date 2020/8/5 9:50 PM
 * @Version
 */
public class PrimitiveCachePoolTest {
    public static void main(String[] args) {
        // ①int 与 int 类型比较
        int i1 = 128;
        int i2 = 128;
        System.out.println("int == int, result:" + (i1 == i2));// true
        System.out.println("----------------------------------------------------");

        // ②int类型 和 new出来的Integer对象 比较
        int i3 = 128;
        Integer i4 = new Integer(128);
        // 在进行运算前，i4会自动拆箱，实质是两个int类型比较
        System.out.println("int == new Integer, result:" + (i3 == i4));// true
        System.out.println("----------------------------------------------------");

        // ③int类型 和 自动装箱的Integer对象 比较
        int i5 = 128;
        Integer i6 = 128;
        // 在进行运算前，i6会自动拆箱，实质是两个int类型比较
        System.out.println("int == Integer autoBoxing, result:" + (i5 == i6));// true
        System.out.println("----------------------------------------------------");

        // ④两个new出来的Integer对象的内存地址比较
        Integer i7 = new Integer(128);
        Integer i8 = new Integer(128);
        System.out.println("new Integer == new Integer, result:" + (i7 == i8));// false
        System.out.println("----------------------------------------------------");

        // ⑤自动装箱的Integer类型 和 new出来的Integer对象 比较
        Integer i9 = 128;
        Integer i10 = new Integer(128);
        System.out.println("Integer autoBoxing == new Integer, result:" + (i9 == i10));// false
        System.out.println("----------------------------------------------------");

        // ⑥自动装箱的Integer对象 和 自动装箱的Integer对象 比较，区间[-128, 127]之外
        Integer i11 = 128;// 缓存池
        Integer i12 = 128;// 缓存池
        System.out.println("Integer autoBoxing == Integer autoBoxing, 区间[-128, 127]之外，result:" + (i11 == i12));// false
        System.out.println("----------------------------------------------------");

        // 自动装箱会从缓存池中取对象，缓存池的区间为[-128, 127]
        // 自动装箱的Integer对象 和 自动装箱的Integer对象 比较，区间[-128, 127]中
        Integer i13 = 127;// 缓存池
        Integer i14 = 127;// 缓存池
        System.out.println("Integer autoBoxing == Integer autoBoxing, 区间[-128, 127]中，result:" + (i13 == i14));// true
        System.out.println("----------------------------------------------------");

        // 自动装箱的Byte对象 和 自动装箱的Byte对象 比较，区间[-128, 127]中
        Byte b1 = 127;// 缓存池
        Byte b2 = 127;// 缓存池
        System.out.println("Byte autoBoxing == Byte autoBoxing, 区间[-128, 127]中，result:" + (b1 == b2));// true
        System.out.println("----------------------------------------------------");

        // 自动装箱的Short对象 和 自动装箱的Short对象 比较，区间[-128, 127]中
        Short s1 = 127;// 缓存池
        Short s2 = 127;// 缓存池
        System.out.println("Short autoBoxing == Short autoBoxing, 区间[-128, 127]中，result:" + (s1 == s2));// true
        System.out.println("----------------------------------------------------");

        // 自动装箱的Long对象 和 自动装箱的Long对象 比较，区间[-128, 127]中
        Long l1 = 127L;// 缓存池
        Long l2 = 127L;// 缓存池
        System.out.println("Long autoBoxing == Long autoBoxing, 区间[-128, 127]中，result:" + (l1 == l2));// true
        System.out.println("----------------------------------------------------");

        // 自动装箱的Character对象 和 自动装箱的Character对象 比较，区间[-128, 127]中
        Character c1 = 127;// 缓存池
        Character c2 = 127;// 缓存池
        System.out.println("Character autoBoxing == Character autoBoxing, 区间[-128, 127]中，result:" + (c1 == c2));// true
        System.out.println("----------------------------------------------------");

        // 自动装箱的Boolean对象 和 自动装箱的Boolean对象 比较，区间[-128, 127]中
        Boolean bool1 = true;// 缓存池
        Boolean bool2 = true;// 缓存池
        System.out.println("Boolean autoBoxing == Boolean autoBoxing, 区间[true, false]中，result:" + (bool1 == bool2));// true
        System.out.println("----------------------------------------------------");
    }
}

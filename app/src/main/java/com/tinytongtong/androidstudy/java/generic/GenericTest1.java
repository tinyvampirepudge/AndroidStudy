package com.tinytongtong.androidstudy.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2020/11/18 9:41 PM
 * @Version
 */
public class GenericTest1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List list2 = list;
        list2.add(1);
        list2.add(2);
        list2.add("abc");
        list2.add(true);
        list2.add(1.54);
        list2.add(new OutOfMemoryError());
        System.out.println(list2);// [1, 2, abc, true, 1.54, java.lang.OutOfMemoryError]

        List<? super Object> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add("abc");
        list3.add(true);
        list3.add(1.54);
        list3.add(new OutOfMemoryError());

        List<?> list4 = new ArrayList<>();
//        list4.add(new Object());
//        list4.add(2);
//        list4.add("abc");
//        list4.add(true);
//        list4.add(1.54);
//        list4.add(new OutOfMemoryError());
    }
}

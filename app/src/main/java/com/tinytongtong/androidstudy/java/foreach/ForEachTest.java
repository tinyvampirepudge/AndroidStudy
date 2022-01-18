package com.tinytongtong.androidstudy.java.foreach;

import java.util.ArrayList;

/**
 * @Description: foreach为何会调用迭代器
 * @Author tinytongtong
 * @Date 2020/8/8 10:30 PM
 * @Version
 */
public class ForEachTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("111");
        arrayList.add("222");

        for (String item : arrayList) {
            System.out.println(item);
        }

        arrayList.add("333");

        for (String item : arrayList) {
            System.out.println(item);
        }
    }
}

package com.tinytongtong.androidstudy.java.container;

import java.util.LinkedHashMap;

/**
 * @Description: LinkedHashMap测试
 * @Author tinytongtong
 * @Date 2020/11/12 9:08 AM
 * @Version
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        System.out.println("——————LinkedHashMap#accessOrder参数默认值为false——————");
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();
        for (int i = 0; i < 5; i++) {
            lhm.put(i, "value" + i);
        }
        System.out.println(lhm);

        // LinkedHashMap#accessOrder参数默认值为false，此时get方法不会改变元素顺序
        // 如果把LinkedHashMap#accessOrder参数值设为true，则get方法就会改变元素顺序，将最近使用的放在尾部
        lhm.get(3);
        System.out.println(lhm);

        System.out.println("——————————————————————————————————————");

        System.out.println("——————LinkedHashMap#accessOrder参数值设为true——————");
        LinkedHashMap<Integer, String> lhm1 = new LinkedHashMap<>(0, 0.75f, true);

        for (int i = 0; i < 5; i++) {
            lhm1.put(i, "value" + i);
        }
        System.out.println(lhm1);

        // LinkedHashMap#accessOrder参数默认值为false，此时get方法不会改变元素顺序
        // 如果把LinkedHashMap#accessOrder参数值设为true，则get方法就会改变元素顺序，将最近使用的放在尾部
        lhm1.get(3);
        System.out.println(lhm1);
    }
}

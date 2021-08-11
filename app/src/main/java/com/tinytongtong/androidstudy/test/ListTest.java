package com.tinytongtong.androidstudy.test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @Description: TODO
 * @Author wangjianzhou
 * @Date 2021/8/5 9:23 PM
 * @Version TODO
 */
public class ListTest {
    public static void main(String[] args) {
//        retainAllTest();

        List<String> dataList = new ArrayList<String>();
//        List<String> dataList = null;
        dataList.add("A");
        dataList.add("B");
        System.out.println("dataList:" + dataList);

        List<String> orderList = new ArrayList<>();
//        List<String> orderList = null;
        orderList.add("A");
        orderList.add("B");
//        orderList.add("B");
//        orderList.add("abc");
        orderList.add("");
        System.out.println("orderList:" + orderList);

        /*
        确定最终顺序：数据优先，兼顾顺序
         */
        // 走兜底的顺序
        if (orderList == null || orderList.isEmpty()) {
            orderList = new ArrayList<>();
            orderList.add("A");
            orderList.add("B");
            System.out.println("走兜底的顺序 orderList:" + orderList);
        }

        List<String> resultOrder = new ArrayList<>();
        if (orderList != null && !orderList.isEmpty()) {
            for (String order : orderList) {
                if (order != null && order.length() != 0
                        && dataList != null && !dataList.isEmpty()
                        && dataList.contains(order)) {
                    System.out.println("orderList中符合条件的数据: " + order);
                    // 避免重复添加
                    if (!resultOrder.contains(order)) {
                        resultOrder.add(order);
                    }
                    dataList.remove(order);
                } else {
                    System.out.println("orderList中不符合条件的数据: " + order);
                }
            }
        }
        // 看数据是否未有完整顺序。
        if (dataList != null && !dataList.isEmpty()) {
            for (String data : dataList) {
                resultOrder.add(data);
                System.out.println("dataList剩余的数据: " + data);
            }
        }

        System.out.println("resultOrder:" + resultOrder);
    }

    /**
     * 交集api测试
     */
    private static void retainAllTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("C");

        List<String> list2 = new ArrayList<String>();
        list2.add("C");
        list2.add("B");
        list2.add("A");
        System.out.println(list1);
        System.out.println(list2);
        // 并集
//        list1.addAll(list2);
        // 去重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);
        // 交集
        list1.retainAll(list2);
        // 差集
//        list1.removeAll(list2);
        System.out.println(list1);
        System.out.println(list2);
    }
}

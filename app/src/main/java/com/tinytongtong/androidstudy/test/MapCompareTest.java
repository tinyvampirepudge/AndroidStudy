package com.tinytongtong.androidstudy.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2020/8/13 2:30 PM
 * @Version TODO
 */
public class MapCompareTest {
    public static void main(String[] args) {
        // 本地map
        Map<String, String> localMap = new HashMap<>();
        localMap.put("1", "1");
        localMap.put("2", "2");
        localMap.put("3", "3");
        localMap.put("4", "4");

        // 网络请求的map
        Map<String, String> netMap = new HashMap<>();
        netMap.put("2", "2");
        netMap.put("3", "3");
        netMap.put("4", "4");
        netMap.put("5", "5");

        // 第一步 求key的交集
        HashSet<String> tempSet = new HashSet<>();
        for (String key : localMap.keySet()) {
            if (netMap.containsKey(key)) {
                tempSet.add(key);
            }
        }
        System.out.println("交集 tempSet：" + tempSet);

        // 第二步 拿 交集 跟 本地map 比较，找出 本地map 需要删除的key
        HashSet<String> deleteSet = new HashSet<>();
        for (String key : localMap.keySet()) {
            if (!tempSet.contains(key)) {
                deleteSet.add(key);
            }
        }
        System.out.println("本地map需要删除的key deleteSet：" + deleteSet);

        // 第三步 拿 交集 跟 网络请求map 比较，找出 本地map 需要增加的key
        HashSet<String> addSet = new HashSet<>();
        for (String key : netMap.keySet()) {
            if (!tempSet.contains(key)) {
                addSet.add(key);
            }
        }
        System.out.println("本地map需要增加的key addSet：" + addSet);
    }
}

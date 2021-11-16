package com.tinytongtong.androidstudy.utils;

import java.util.List;

/**
 * @Description: Log公交类
 * @Author wangjianzhou
 * @Date 2021/10/26 5:10 PM
 */
public class LogUtil {

    /**
     * 将List数据换行打印
     *
     * @param list
     */
    public static <T> void logList(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            String content = list.get(i) == null ? "null" : list.get(i).toString();
            System.out.println(String.format("index: %s, content: %s", i, content));
        }
    }
}

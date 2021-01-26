package com.tinytongtong.androidstudy.viewpager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2021/1/22 10:26 AM
 * @Version
 */
public class TabBeanFactory {
    public static int index = 0;

    public static List<TabBean> getTabData() {
        List<TabBean> classifyModels = new ArrayList<>();
        classifyModels.add(new TabBean(1, String.format("王蛋蛋:%s", 1)));
        classifyModels.add(new TabBean(2, String.format("王蛋蛋:%s", 2)));
        classifyModels.add(new TabBean(3, String.format("王蛋蛋:%s", 3)));
        int random = index++ % 5;
        for (int i = 0; i < random; i++) {
            classifyModels.add(new TabBean(100 + i, String.format("王蛋蛋的爸比:%s", i)));
        }
        return classifyModels;
    }
}

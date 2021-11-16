package com.tinytongtong.androidstudy.test;

import com.tinytongtong.androidstudy.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 不同类型的List排序
 * @Author wangjianzhou
 * @Date 2021/10/26 4:42 PM
 */
public class ListSortTest {

    public static class SortBase {
        public int displayIndex;

        public SortBase(int displayIndex) {
            this.displayIndex = displayIndex;
        }

        @Override
        public String toString() {
            return "SortBase{" +
                    "displayIndex=" + displayIndex +
                    '}';
        }
    }

    public static class CommonData extends SortBase {
        public String name;

        public CommonData(int displayIndex, String name) {
            super(displayIndex);
            this.name = name;
        }

        @Override
        public String toString() {
            return "CommonData{" +
                    "displayIndex=" + displayIndex +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class YardData extends SortBase {
        public int age;

        public YardData(int displayIndex, int age) {
            super(displayIndex);
            this.age = age;
        }

        @Override
        public String toString() {
            return "YardData{" +
                    "displayIndex=" + displayIndex +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        for (String item: strings) {
            System.out.println(String.format("strings item:",item));
        }
        System.out.println();

        List<CommonData> commonDataList = new ArrayList<>();
        for (int i = -1; i < 10; i++) {
            if (i % 2 == 1) {
                continue;
            }
            CommonData commonData = new CommonData(i, "CommonData" + i);
            commonDataList.add(commonData);
        }

        List<YardData> yardDataList = new ArrayList<>();
        for (int i = -1; i < 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            YardData yardData = new YardData(i, 100 + i);
            yardDataList.add(yardData);
        }

        LogUtil.logList(commonDataList);
        LogUtil.logList(yardDataList);

        List<SortBase> allList = new ArrayList<>();
        allList.addAll(commonDataList);
        allList.addAll(yardDataList);

        LogUtil.logList(allList);

        // 排序
        Collections.sort(allList, new Comparator<SortBase>() {
            @Override
            public int compare(SortBase o1, SortBase o2) {
                return o1.displayIndex - o2.displayIndex;
            }
        });
        System.out.println("after sort");
        LogUtil.logList(allList);

        System.out.println("查看元素细节：");
        for (int i = 0; i < allList.size(); i++) {
            SortBase item = allList.get(i);
            if (item == null) {
                continue;
            }
            if (item instanceof YardData) {
                YardData yardData = (YardData) item;
                System.out.println(String.format("index: %s, yardData.age: %s", i, yardData.age));
            } else if (item instanceof CommonData) {
                CommonData commonData = (CommonData) item;
                System.out.println(String.format("index: %s, commonData.name: %s", i, commonData.name));
            }
        }
    }
}

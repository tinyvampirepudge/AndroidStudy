package com.tinytongtong.androidstudy.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 3/25/21 11:29 AM
 */
public class StringUtils {
    public static void main(String[] args) {
        String name = "name";
        List<String> aliasName = new ArrayList<>();
//        aliasName.add("别名1");
//        aliasName.add("别名2");
//        aliasName.add("别名3");
//        aliasName.add("别名4");
        List<String> oldName = new ArrayList<>();
        oldName.add("曾用名1");
        oldName.add("曾用名2");
//        oldName.add("曾用名3");
//        oldName.add("曾用名4");
        String result = appendAliasAndOldName(name, aliasName, oldName);
        /**
         * 测试case :
         * —— name为空，name为null
         * —— 别名
         *      —— 别名为null， 别名为[]
         *      —— 别名部分数据为空或null
         *      —— 别名全部数据为空或null
         * —— 曾用名为null， 曾用名为[]
         *      —— 曾用名为null， 曾用名为[]
         *      —— 曾用名部分数据为空或null
         *      —— 曾用名全部数据为空或null
         */
        System.out.println(String.format("拼接结果: %s", result));

        System.out.println("拼接结果313123123: " + null);
    }

    private static String appendAliasAndOldName(String name, List<String> aliasName, List<String> oldName) {
        // 避免StringBuilder拼接奔溃
        if (name == null) {
            name = "";
        }
        StringBuilder sb = new StringBuilder(name);
        // 别名和曾用名使用中文括号包裹起来
        String leftCurves = "（";
        String rightCurves = "）";
        String innerSplit = "、"; // 别名和曾用名内部的分隔符
        String prefixOld = "原:"; // 只有曾用名是的前缀
        String prefixAlias = "或:"; // 只有别名是的前缀
        String groupSplit = " 或 "; // 别名和曾用名同时存在时，中间的分隔符

        String aliasValue = getStrListValue(innerSplit, aliasName);
        String oldValue = getStrListValue(innerSplit, oldName);
        // 别名和曾用名均不为空
        if (aliasValue != null && aliasValue.length() > 0 && oldValue != null && oldValue.length() > 0) {
            sb.append(leftCurves)
                    .append(aliasValue)
                    .append(groupSplit)
                    .append(oldValue)
                    .append(rightCurves);
            return sb.toString();
        }
        // 别名不为空，曾用名为空
        if (aliasValue != null && aliasValue.length() > 0) {
            sb.append(leftCurves)
                    .append(prefixAlias)
                    .append(aliasValue)
                    .append(rightCurves);
            return sb.toString();
        }
        // 曾用名不为空，别名为空
        if (oldValue != null && oldValue.length() > 0) {
            sb.append(leftCurves)
                    .append(prefixOld)
                    .append(oldValue)
                    .append(rightCurves);
            return sb.toString();
        }
        return sb.toString();
    }

    private static String getStrListValue(String split, List<String> strs) {
        if (strs == null || strs.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        int validCount = 0;
        for (String item : strs) {
            if (item != null && item.length() > 0) {
                validCount++;
            }
        }
        if (validCount == 0) {
            return null;
        }
        String[] array = new String[validCount];
        int index = 0;
        for (int i = 0; i < strs.size(); i++) {
            if (strs.get(i) != null && strs.get(i).length() > 0) {
                array[index++] = strs.get(i);
            }
        }
        String result = appendStringArrays(split, array);
        return result;
    }

    /**
     * 通过分隔符拼接字符串
     *
     * @param separator
     * @param args
     * @return
     */
    public static String appendStringArrays(String separator, String... args) {
        if (args == null) {
            return null;
        }
        int length = args.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(args[i]);
            if (i != length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

}

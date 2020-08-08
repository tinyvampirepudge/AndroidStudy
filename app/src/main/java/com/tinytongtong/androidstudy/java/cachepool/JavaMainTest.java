package com.tinytongtong.androidstudy.java.cachepool;

/**
 * @Description: 执行Java main方法
 *
 * @Author tinytongtong
 * @Date 2020/8/8 11:39 AM
 * @Version
 */
public class JavaMainTest {
    /**
     * 需要在package对应的父目录中执行java命令，不然会报找不到主类异常。
     * @param args
     */
    public static void main(String[] args) {
        for (String item: args) {
            System.out.println(item);
        }
    }
}

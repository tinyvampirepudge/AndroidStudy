package com.tinytongtong.androidstudy.test;

import java.text.DecimalFormat;

/**
 * @Description: TODO
 *
 * @Author tinytongtong
 * @Date 2020/11/5 5:23 PM
 * @Version TODO
 */
public class NumberFormatTest {

    private static String getNumWith2Dot(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }

    public static void main(String[] args) {
        System.out.println(getNumWith2Dot(20d));
        System.out.println(getNumWith2Dot(20.04));
        System.out.println(getNumWith2Dot(20.044));
        System.out.println(getNumWith2Dot(0.13));
    }
}

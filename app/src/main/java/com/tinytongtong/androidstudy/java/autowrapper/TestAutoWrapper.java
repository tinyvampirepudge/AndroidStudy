package com.tinytongtong.androidstudy.java.autowrapper;

import java.util.ArrayList;

/**
 * @Description: 自动装箱和拆箱
 * @Author wangjianzhou
 * @Date 2020/8/3 11:27 PM
 * @Version
 */
class TestAutoWrapper {
    public static void main(String[] args) {
        /**
         * 基本类型的包装器类：Integer、Long、Float、Double、Short、Byte、Character、Boolean和Void
         */
        // int类型的自动装箱和拆箱
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
        integerArrayList.add(1);
        // INVOKEVIRTUAL java/lang/Integer.intValue ()I
        int i = integerArrayList.get(0);

        // long类型的自动装箱和拆箱
        ArrayList<Long> longArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Long.valueOf (J)Ljava/lang/Long;
        longArrayList.add(10L);
        // INVOKEVIRTUAL java/lang/Long.longValue ()J
        long l = longArrayList.get(0);

        // float类型的自动装箱和拆箱
        ArrayList<Float> floatArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Float.valueOf (F)Ljava/lang/Float;
        floatArrayList.add(1.0f);
        // INVOKEVIRTUAL java/lang/Float.floatValue ()F
        float f = floatArrayList.get(0);

        // double类型的自动装箱和拆箱
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Double.valueOf (D)Ljava/lang/Double;
        doubleArrayList.add(1.0d);
        // INVOKEVIRTUAL java/lang/Double.doubleValue ()D
        double d = doubleArrayList.get(0);

        // short类型的自动装箱和拆箱
        ArrayList<Short> shortArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Short.valueOf (S)Ljava/lang/Short;
        shortArrayList.add((short) 10);
        // INVOKEVIRTUAL java/lang/Short.shortValue ()S
        short s = shortArrayList.get(0);

        // byte类型的自动装箱和拆箱
        ArrayList<Byte> byteArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Byte.valueOf (B)Ljava/lang/Byte;
        byteArrayList.add((byte) 10);
        // INVOKEVIRTUAL java/lang/Byte.byteValue ()B
        byte b = byteArrayList.get(0);

        // char类型的自动装箱和拆箱
        ArrayList<Character> characterArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Character.valueOf (C)Ljava/lang/Character;
        characterArrayList.add((char) 10);
        // INVOKEVIRTUAL java/lang/Character.charValue ()C
        char c = characterArrayList.get(0);

        // boolean类型的自动装箱和拆箱
        ArrayList<Boolean> booleanArrayList = new ArrayList<>();
        // INVOKESTATIC java/lang/Boolean.valueOf (Z)Ljava/lang/Boolean;
        booleanArrayList.add(true);
        // INVOKEVIRTUAL java/lang/Boolean.booleanValue ()Z
        boolean bool = booleanArrayList.get(0);
    }
}

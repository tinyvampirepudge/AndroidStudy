package com.tinytongtong.androidstudy.java.autowrapper;

/**
 * @Description: 自动装箱的陷阱
 * 深入理解Java虚拟机10-13
 * @Author wangjianzhou
 * @Date 2020/8/21 9:51 AM
 * @Version
 */
public class TestAutoWrapper1 {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);// true  比较内存地址，位于缓存池范围内
        System.out.println(e == f);// false  比较内存地址，，位于缓存池范围外
        System.out.println(c == (a + b));// true a和b先拆箱做算术运算，然后c也拆箱，然后做比较
        System.out.println(c.equals(a + b));// true a和b先拆箱做算术运算，然后对结果做装箱，接着跟c做比较
        System.out.println(g == (a + b));// true g先拆箱成long，然后a和b拆箱成int做算术运算得到运算结果，接着将运算结果转型成功long类型，然后跟g比较
        System.out.println(g.equals(a + b));// false a和b先拆箱做算术运算，然后将结果装箱成Integer，接着跟g做比较
    }
}

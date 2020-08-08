package com.tinytongtong.androidstudy.java.intanceof;

/**
 * @Description: instanceof 和 Class#isInstance方法 测试
 * {@link Class#isInstance(Object)}
 * @Author tinytongtong
 * @Date 2020/8/8 4:42 PM
 * @Version
 */
public class InstanceOfTest {
    /*
    Class#isInstance(Object)是 Java 语言 instanceof 运算符的动态等效方法
    判定指定的 Object 是否与此 Class 所表示的对象赋值兼容。此方法是 Java 语言 instanceof 运算符的动态等效方法。
    如果指定的 Object 参数非空，且能够在不引发 ClassCastException 的情况下被强制转换成该 Class 对象所表示的引用类型，则该方法返回 true；否则返回 false。

    特别地，当该 Class 对象表示一个已声明的类时，若指定的 Object 参数是所表示类（或其任一子类）的一个实例，则此方法返回 true；否则返回 false。

    如果此 Class 对象表示一个数组类，且通过身份转换或扩展引用转换，指定的 Object 参数能转换为一个数组类的对象，则返回 true；否则返回 false。

    如果此 Class 对象表示一个接口，且指定 Object 参数的类或任一超类实现了此接口，则此方法返回 true；否则返回 false。如果此 Class 对象表示一个基本类型，
    则此方法返回 false。
     */
    public static void main(String[] args) {
        A a1 = new A() {
        };
        B b1 = new B();
        C c1 = new C();
        D d1 = new D();

        // instanceof 运算符测试
        System.out.println("instanceof 运算符测试 start");

        System.out.println("子类的实例匹配父类类型，父类实例不匹配子类类型:包括接口");
        System.out.println("A的实例 instanceof A：" + (a1 instanceof A));// true
        System.out.println("A的实例 instanceof C：" + (a1 instanceof C));// false
        System.out.println("B的实例 instanceof B：" + (b1 instanceof B));// true
        System.out.println("B的实例 instanceof D：" + (b1 instanceof D));// false
        System.out.println("----------------------------------------");

        System.out.println("子类的实例匹配父类类型:包括接口");
        System.out.println("C的实例 instanceof C：" + (c1 instanceof C));// true
        System.out.println("C的实例 instanceof A：" + (c1 instanceof A));// true
        System.out.println("D的实例 instanceof D：" + (d1 instanceof D));// true
        System.out.println("D的实例 instanceof B：" + (d1 instanceof B));// true
        System.out.println("----------------------------------------");

        // 所有对象都是object
        System.out.println("所有接口和类都是继承自Object:");
        System.out.println("A的实例 instanceof D：" + (a1 instanceof Object));// true
        System.out.println("B的实例 instanceof B：" + (b1 instanceof Object));// true
        System.out.println("C的实例 instanceof D：" + (c1 instanceof Object));// true
        System.out.println("D的实例 instanceof B：" + (d1 instanceof Object));// true
        System.out.println("----------------------------------------");

        // 实例为null的都是false
        System.out.println("实例为null的都是false:");
        System.out.println("null instanceof A：" + (null instanceof A));// false
        System.out.println("null instanceof C：" + (null instanceof C));// false
        System.out.println("null instanceof B：" + (null instanceof B));// false
        System.out.println("null instanceof D：" + (null instanceof D));// false
        System.out.println("----------------------------------------");

        System.out.println("instanceof 运算符测试 end");

        // Class#isInstance(Object)方法测试
        System.out.println("Class#isInstance(Object)方法测试 start");

        System.out.println("子类的实例匹配父类类型，父类实例不匹配子类类型:包括接口");
        System.out.println("A.class.isInstance(A的实例)：" + A.class.isInstance(a1));// true
        System.out.println("C.class.isInstance(A的实例)：" + C.class.isInstance(a1));// false
        System.out.println("B.class.isInstance(B的实例)：" + B.class.isInstance(b1));// true
        System.out.println("D.class.isInstance(B的实例)：" + D.class.isInstance(b1));// false
        System.out.println("----------------------------------------");

        System.out.println("子类的实例匹配父类类型:包括接口");
        System.out.println("A.class.isInstance(C的实例)：" + A.class.isInstance(c1));// true
        System.out.println("C.class.isInstance(C的实例)：" + C.class.isInstance(c1));// true
        System.out.println("B.class.isInstance(D的实例)：" + B.class.isInstance(d1));// true
        System.out.println("D.class.isInstance(D的实例)：" + D.class.isInstance(d1));// true
        System.out.println("----------------------------------------");

        // 所有对象都是object
        System.out.println("所有接口和类都是继承自Object:");
        System.out.println("Object.class.isInstance(A的实例)：" + Object.class.isInstance(a1));// true
        System.out.println("Object.class.isInstance(B的实例)：" + Object.class.isInstance(b1));// true
        System.out.println("Object.class.isInstance(C的实例)：" + Object.class.isInstance(c1));// true
        System.out.println("Object.class.isInstance(D的实例)：" + Object.class.isInstance(d1));// true
        System.out.println("----------------------------------------");

        // 实例为null的都是false
        System.out.println("实例为null的都是false:");
        System.out.println("A.class.isInstance(null)：" + A.class.isInstance(null));// false
        System.out.println("C.class.isInstance(null)：" + C.class.isInstance(null));// false
        System.out.println("B.class.isInstance(null)：" + B.class.isInstance(null));// false
        System.out.println("D.class.isInstance(null)：" + D.class.isInstance(null));// false
        System.out.println("----------------------------------------");

        System.out.println("Class#isInstance(Object)方法测试 end");
    }
}

interface A {

}

class B {

}

class C implements A {

}

class D extends B {

}
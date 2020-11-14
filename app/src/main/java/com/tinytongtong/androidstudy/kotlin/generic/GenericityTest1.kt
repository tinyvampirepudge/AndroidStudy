package com.tinytongtong.androidstudy.kotlin.generic

/**
 * @Description: 泛型——型变
 * https://www.kotlincn.net/docs/reference/generics.html
 *
 * @Author tinytongtong
 * @Date 2020/11/14 10:49 AM
 * @Version
 */

/**
 * Java 类型系统中最棘手的部分之一是通配符类型（参见 Java Generics FAQ）。而 Kotlin 中没有。
 * 相反，它有两个其他的东西：声明处型变（declaration-site variance）与类型投影（type projections）。
 */

/**
 * 声明处型变：我们可以标注 Source 的类型参数 T 来确保它仅从 Source<T> 成员中返回（生产），并从不被消费。
 * 为此，我们提供 out 修饰符：
 */


open class Parent

open class Child : Parent()

open class GrandSon : Child()

interface Source<out T> {
    fun nextT(): T
}

fun demo1(strs: Source<String>) {
    val objects: Source<Any> = strs
}

fun demo11(strs: Source<Child>) {
    val objects: Source<Parent> = strs
}

/**
 * 另外除了 out，Kotlin 又补充了一个型变注释：in。它使得一个类型参数逆变：只可以被消费而不可以被生产。
 * 逆变类型的一个很好的例子是 Comparable：
 */
interface Comparable1<in T> {
    operator fun compareTo(other: T): Int
}

fun demo2(x: Comparable1<Number>) {
    x.compareTo(1.0)
    val y: Comparable1<Double> = x
}

fun demo21(x: Comparable1<Child>) {
    x.compareTo(GrandSon())
    val y: Comparable1<GrandSon> = x
}
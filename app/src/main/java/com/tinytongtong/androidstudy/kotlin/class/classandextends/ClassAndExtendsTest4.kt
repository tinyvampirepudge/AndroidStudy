package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：继承Any
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:41 PM
 * @Version
 */

/**
 * 在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类：
 * Any 有三个方法：equals()、 hashCode() 与 toString()。因此，为所有 Kotlin 类都定义了这些方法。
 */
class Example

fun main(args: Array<String>) {
    val e = Example()
    val e1 = Example()
    println(e == e1)
    println(e.hashCode())
    println(e.toString())
}


package com.tinytongtong.androidstudy.kotlin.`class`.nestedandinner

/**
 * @Description: 内部类
 * https://www.kotlincn.net/docs/reference/nested-classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 9:46 PM
 * @Version
 */

class Outer1 {
    private val bar: Int = 100

    /**
     * 标记为 inner 的嵌套类能够访问其外部类的成员。内部类会带有一个对外部类的对象的引用：
     */
    inner class Inner1 {
        fun foo() = bar
    }
}

fun main(args: Array<String>) {
    val demo = Outer1().Inner1().foo()
    println(demo)
}
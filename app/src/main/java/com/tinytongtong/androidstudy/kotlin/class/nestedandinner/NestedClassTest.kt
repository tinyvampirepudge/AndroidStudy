package com.tinytongtong.androidstudy.kotlin.`class`.nestedandinner

/**
 * @Description: 嵌套类与内部类
 * https://www.kotlincn.net/docs/reference/nested-classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 8:11 PM
 * @Version
 */

/**
 * 类可以嵌套在其他类中：
 */
class Outer {
    private val bar: Int = 1

    /**
     * 默认就是static效果
     */
    class Nested {
        fun foo() = 2
    }
}

fun main(args: Array<String>) {
    val demo = Outer.Nested().foo() // 2
    println(demo)
}

package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展——扩展的作用域
 * https://www.kotlincn.net/docs/reference/extensions.html
 *
 * @Author tinytongtong
 * @Date 2020/11/13 9:28 AM
 * @Version
 */

/**
 * 大多数时候我们在顶层定义扩展——直接在包里：
 */
fun main(args: Array<String>) {
    val list = mutableListOf(1, 2, 3)
    println(list.lastIndex)
}

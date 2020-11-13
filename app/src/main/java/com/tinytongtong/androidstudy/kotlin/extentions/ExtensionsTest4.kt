package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展——扩展属性
 * https://www.kotlincn.net/docs/reference/extensions.html
 *
 * @Author tinytongtong
 * @Date 2020/11/13 9:28 AM
 * @Version
 */


/**
 * 与函数类似，Kotlin 支持扩展属性。
 * 注意：由于扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。
 * 这就是为什么扩展属性不能有初始化器。他们的行为只能由显式提供的 getters/setters 定义。
 */
val <T> List<T>.lastIndex: Int
    get() = size - 1

//var String.number = 1 // 错误：扩展属性不能有初始化器

fun main(args: Array<String>) {
    val list = mutableListOf(1, 2, 3)
    println(list.lastIndex)
}

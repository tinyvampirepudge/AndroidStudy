package com.tinytongtong.androidstudy.kotlin.generic

/**
 * @Description: 泛型
 * https://www.kotlincn.net/docs/reference/generics.html
 *
 * @Author tinytongtong
 * @Date 2020/11/14 10:38 AM
 * @Version
 */

class Box<T>(t: T) {
    val value = t
}


fun main(args: Array<String>) {
    val box: Box<Int> = Box(1)
    println(box.value)
    println(box.javaClass.simpleName)

    val box1 = Box(1)
}
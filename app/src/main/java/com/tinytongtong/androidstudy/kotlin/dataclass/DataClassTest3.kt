package com.tinytongtong.androidstudy.kotlin.dataclass

/**
 * @Description: 数据类——数据类与解构声明
 *
 * @Author tinytongtong
 * @Date 2020/11/14 9:49 AM
 * @Version
 */

fun main(args: Array<String>) {
    val jane = User1("Jane", 35)
    val (name, age) = jane
    println("name:$name, aeg:$age")
}

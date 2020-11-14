package com.tinytongtong.androidstudy.kotlin.dataclass

/**
 * @Description: 数据类——复制
 *
 * @Author tinytongtong
 * @Date 2020/11/14 10:14 AM
 * @Version
 */

data class User1(val name: String, val age: Int)

fun main(args: Array<String>) {
    val jack = User1(name = "Jack", age = 10)
    val jackCopy = jack.copy(age = 2)
    println(jack)
    println(jackCopy)
}

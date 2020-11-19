package com.tinytongtong.androidstudy.kotlin.lambda

/**
 * @Description: use：内联，扩展函数
 *
 * 跟let很像，但是返回的是自身
 * @Author tinytongtong
 * @Date 2020/11/19 10:47 AM
 * @Version
 */

fun main(args: Array<String>) {
    val user = User("abc", 100)
    val result = user?.also {
        println("also name:${it.name}, age:${it.age}")
        1000
    }
    println(result)
}
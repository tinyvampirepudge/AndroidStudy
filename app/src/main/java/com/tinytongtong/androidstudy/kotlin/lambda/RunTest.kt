package com.tinytongtong.androidstudy.kotlin.lambda

/**
 * @Description: run：内联，扩展函数，是let和with的结合体
 * https://blog.csdn.net/u013064109/article/details/78786646
 *
 * @Author tinytongtong
 * @Date 2020/11/19 10:47 AM
 * @Version
 */

fun main(args: Array<String>) {
    val user = User("abc", 100)
    val result = user.run {
        println("run name:$name, age:$age")
        1000
    }
    println(result)
}
package com.tinytongtong.androidstudy.kotlin.lambda

/**
 * @Description: with：内联，非扩展函数
 * https://blog.csdn.net/u013064109/article/details/78786646
 *
 * @Author tinytongtong
 * @Date 2020/11/19 10:47 AM
 * @Version
 */
class User(val name: String, val age: Int)

fun main(args: Array<String>) {
    val user = User("abc", 100)
    val result = with(user) {
        println("name:$name, age:$age")
        1000
    }
    println(result)
}
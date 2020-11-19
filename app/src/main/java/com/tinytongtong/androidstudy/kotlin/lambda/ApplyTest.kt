package com.tinytongtong.androidstudy.kotlin.lambda

/**
 * @Description: apply：内联，扩展函数
 * https://blog.csdn.net/u013064109/article/details/78786646
 * 从结构上来看apply函数和run函数很像，唯一不同点就是它们各自返回的值不一样，
 * run函数是以闭包形式返回最后一行代码的值，而apply函数的返回的是传入对象的本身。
 * @Author tinytongtong
 * @Date 2020/11/19 10:47 AM
 * @Version
 */

fun main(args: Array<String>) {
    val user = User("abc", 100)
    val result = user?.apply {
        println("apply name:$name, age:$age")
        1000
    }
    println(result)
}
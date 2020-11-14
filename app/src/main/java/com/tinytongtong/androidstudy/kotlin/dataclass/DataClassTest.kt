package com.tinytongtong.androidstudy.kotlin.dataclass

/**
 * @Description: 数据类
 *
 * @Author tinytongtong
 * @Date 2020/11/14 9:44 AM
 * @Version
 */

data class User(val name: String, val age: Int)

fun main(args: Array<String>) {
    val user = User("王蛋蛋的爸比", 18)
    val userCopy = user.copy("王蛋蛋的妈咪", 16)
    println(user)
    println(userCopy)

    // 解构声明
    val name = user.component1()
    val age = user.component2()

}
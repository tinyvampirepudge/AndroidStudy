package com.tinytongtong.androidstudy.kotlin.gettersetter

/**
 * @Description: 重写getter和setter方法
 *
 * @Author tinytongtong
 * @Date 2020/11/12 11:57 AM
 * @Version
 */

class Person1(var gender: Gender) {
    var name: String = ""
        set(value) {
            field = when (gender) {
                Gender.MALE -> "Jake.$value"
                Gender.FEMALE -> "Rose.$value"
            }
        }
}

enum class Gender {
    MALE, FEMALE
}

fun main(args: Array<String>) {
    // 性别MALE
    var person = Person1(Gender.MALE)
    person.name = "Love"
    println("打印结果：${person.name}")

    // 性别FEMALE
    var person1 = Person1(Gender.FEMALE)
    person1.name = "Love"
    println("打印结果：${person1.name}")

}
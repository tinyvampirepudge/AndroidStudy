package com.tinytongtong.androidstudy.kotlin.gettersetter

/**
 * @Description: getter和setter
 *
 * @Author tinytongtong
 * @Date 2020/11/12 11:30 AM
 * @Version
 */

class Person {
    var name: String = ""
        get() = field
        set(value) {
            // 幕后字段field，幕后字段主要用于自定义getter和setter中，并且只能在getter 和setter中访问。
            field = value
        }
}

/**
 * 默认实现
 */
class PersonCopy {
    var name: String = ""
}

fun main(args: Array<String>) {
    var person = Person()
    person.name = "Hi, this is new value"
    val name = person.name
    println("打印结果:$name")
}
package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：次构造函数
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:41 PM
 * @Version
 */

/**
 * 类也可以声明前缀有 constructor的次构造函数：
 */
class MinorConstructor(val name: String) {

    constructor(name: String, age: Int) : this(name) {
        println("MinorConstructor#constructor(name: String, age: Int): $name $age")
    }

    init {
        println("MinorConstructor#init $name")
    }
}

fun main(args: Array<String>) {
    val mc = MinorConstructor("猫了个咪")
    val mc1 = MinorConstructor("猫了个咪", 100)
}

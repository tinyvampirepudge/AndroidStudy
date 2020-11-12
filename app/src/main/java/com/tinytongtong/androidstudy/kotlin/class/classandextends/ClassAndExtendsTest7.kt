package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：派生类初始化顺序
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 4:26 PM
 * @Version
 */

open class Base1(val name: String) {
    init {
        println("Base1#init")
    }

    open val size: Int = name.length.also {
        println("initializing size in base: $it")
    }
}

class Derived1(
    name: String,
    val lastName: String,
) : Base1(name.capitalize().also { println("Argument for Base: $it") }) {
    init {
        println("Derived1#init")
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size inn Derived: $it") }
}

fun main(args: Array<String>) {
    val d = Derived1("王蛋蛋", "旺财")
}


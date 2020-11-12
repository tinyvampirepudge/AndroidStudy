package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:41 PM
 * @Version
 */

/**
 * 主构造函数不能包含任何的代码。
 * 初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中。
 * 在实例初始化期间，初始化块按照它们出现在类体中的顺序执行，与属性初始化器交织在一起：
 */
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

fun main(args: Array<String>) {
    val iod = InitOrderDemo("猫了个咪")
}
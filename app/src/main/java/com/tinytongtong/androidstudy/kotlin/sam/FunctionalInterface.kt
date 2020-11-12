package com.tinytongtong.androidstudy.kotlin.sam

/**
 * @Description: 函数式接口
 * https://www.kotlincn.net/docs/reference/fun-interfaces.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:05 PM
 * @Version
 */

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

val isEven = IntPredicate { i -> i % 2 == 0 }

fun main(args: Array<String>) {
    println("Is 7 even? - ${isEven.accept(7)}")
}


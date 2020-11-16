package com.tinytongtong.androidstudy.kotlin.function

/**
 * @Description: 函数
 * https://www.kotlincn.net/docs/reference/functions.html
 *
 * @Author tinytongtong
 * @Date 2020/11/15 11:03 PM
 * @Version
 */

class FunctionTest {
    val s: String = "FunctionTest field"
    fun foo(value: String) {
        println("FunctionTest#foo value:$value")
        fun boo() {
            println("FunctionTest#foo#boo field:$s")
        }
        boo()
    }
}

fun main(args: Array<String>) {
    FunctionTest().foo("abc")
}
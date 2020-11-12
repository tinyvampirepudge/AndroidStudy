package com.tinytongtong.androidstudy.kotlin.companion

/**
 * @Description:
 *
 * @Author tinytongtong
 * @Date 2020/11/11 1:25 PM
 * @Version
 */
class NumberTest {
    companion object Obj {
        @JvmField
        var flag = true

        const val show = false

        @JvmStatic
        fun plus(num1: Int, num2: Int): Int {
            return num1 + num2
        }
    }
}

fun main(args: Array<String>) {
    println(NumberTest.flag)
    println(NumberTest.plus(1, 2))
}
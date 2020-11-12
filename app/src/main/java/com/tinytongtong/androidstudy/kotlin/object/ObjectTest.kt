package com.tinytongtong.androidstudy.kotlin.`object`

/**
 * @Description: object用法
 *
 * @Author tinytongtong
 * @Date 2020/11/11 2:16 PM
 * @Version
 */

object ObjectTest {
    fun show(content: String?) {
        println("show:" + content)
    }
}

fun main(args: Array<String>) {
    ObjectTest.show("abc")
}
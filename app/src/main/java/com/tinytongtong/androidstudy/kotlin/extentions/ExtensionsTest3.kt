package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展——可空接收者
 * https://www.kotlincn.net/docs/reference/extensions.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 11:17 PM
 * @Version
 */

/**
 * 注意可以为可空的接收者类型定义扩展。
 * 这样的扩展可以在对象变量上调用， 即使其值为 null，并且可以在函数体内检测 this == null，
 * 这能让你在没有检测 null 的时候调用 Kotlin 中的toString()：检测发生在扩展函数的内部。
 */
fun main(args: Array<String>) {
    fun Any?.toString():String{
        if (this == null) {
            return "null"
        }
        return toString()
    }

    println(null.toString())
    println("asdfas".toString())
}

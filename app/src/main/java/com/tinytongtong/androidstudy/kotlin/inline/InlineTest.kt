package com.tinytongtong.androidstudy.kotlin.inline

/**
 * @Description: 内联
 * https://www.kotlincn.net/docs/reference/inline-classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/15 10:46 AM
 * @Version
 */

inline class Password(val value: String)

fun main(args: Array<String>) {
    val securePassword = Password("Don't try this in production")
    println(securePassword)
}
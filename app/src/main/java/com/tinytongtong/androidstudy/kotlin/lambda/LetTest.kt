package com.tinytongtong.androidstudy.kotlin.lambda

/**
 * @Description: let：内联、扩展函数
 * https://blog.csdn.net/u013064109/article/details/78786646
 *
 * @Author tinytongtong
 * @Date 2020/11/19 10:47 AM
 * @Version
 */
fun main(args: Array<String>) {
    var s = "abc"
    s.let {
        println(it)
    }
}
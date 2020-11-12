package com.tinytongtong.androidstudy.kotlin.`interface`

/**
 * @Description: 接口
 * https://www.kotlincn.net/docs/reference/interfaces.html
 * @Author tinytongtong
 * @Date 2020/11/12 2:14 PM
 * @Version
 */

interface MyInterface {
    fun bar()
    fun foo() {
        // 可选的方法体
        println("MyInterface#foo")
    }
}

/**
 * 实现接口。
 * 一个类或者对象可以实现一个或多个接口
 */
class Child : MyInterface {
    override fun bar() {
        println("Child#bar")
    }
}

fun main(args: Array<String>) {
    val child = Child()
    child.bar()
    child.foo()
}
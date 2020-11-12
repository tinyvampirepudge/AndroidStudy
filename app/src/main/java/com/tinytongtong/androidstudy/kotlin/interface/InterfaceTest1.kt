package com.tinytongtong.androidstudy.kotlin.`interface`

/**
 * @Description: 接口
 * https://www.kotlincn.net/docs/reference/interfaces.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:19 PM
 * @Version
 */

interface MyInterface1 {
    val prop: Int // 抽象的
    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        println(prop)
    }
}

class Child1 : MyInterface1 {
    override val prop: Int
        get() = 29
}

fun main(args: Array<String>) {
    val child = Child1()
    child.foo()
    println(child.prop)
    println(child.propertyWithImplementation)
}
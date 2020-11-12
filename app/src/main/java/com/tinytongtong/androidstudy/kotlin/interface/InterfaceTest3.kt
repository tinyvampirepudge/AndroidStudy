package com.tinytongtong.androidstudy.kotlin.`interface`

/**
 * @Description: 解决覆盖冲突
 * https://www.kotlincn.net/docs/reference/interfaces.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:29 PM
 * @Version
 */

interface A {
    fun foo() {
        println("A#foo")
    }

    fun bar()
}

interface B {
    fun foo() {
        println("B#foo")
    }

    fun bar() {
        println("B#bar")
    }
}

class C : A {
    override fun bar() {
        println("C#bar")
    }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
        println("D#foo")
    }

    override fun bar() {
        println("D#bar")
        super.bar()
    }
}

fun main(args: Array<String>) {
    val d = D()
    d.foo()
    d.bar()
}

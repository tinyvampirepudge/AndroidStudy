package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：抽象类
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 5:37 PM
 * @Version
 */

/**
 * 类以及其中的某些成员可以声明为 abstract。 抽象成员在本类中可以不用实现。
 * 需要注意的是，我们并不需要用 open 标注一个抽象类或者函数——因为这不言而喻。
 */
open class Polygon4 {
    open fun draw() {
        println("Polygon4#draw")
    }
}

/**
 * 我们可以用一个抽象成员覆盖一个非抽象的开放成员
 */
abstract class Rectangle4 : Polygon4() {
    abstract override fun draw()
    fun show() {
        println("Rectangle4#show")
    }
}

fun main(args: Array<String>) {
    val r = object : Rectangle4() {
        override fun draw() {
            println("Rectangle4 instance draw")
        }
    }
    r.draw()
    r.show()
}


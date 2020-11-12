package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：覆盖方法，覆盖属性
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 4:26 PM
 * @Version
 */

open class Shape {
    open fun draw() {
        println("Shape#draw")
    }

    fun fill() {
        println("Shape#fill")
    }

    open val vertexCount: Int = 0
}

class Circle : Shape() {
    override fun draw() {
//        super.draw()
        println("Circle#draw")
    }
}

open class Rectangle : Shape() {
    final override fun draw() {
        super.draw()
    }

    override val vertexCount: Int
        get() = 4
}

interface ShapeInterface {
    val vertexCount: Int
}

class Rectangle1(override val vertexCount: Int = 4) : ShapeInterface

class Polygon : ShapeInterface {
    override var vertexCount: Int = 0// 可以设置为任何数
}

fun main(args: Array<String>) {
    val c = Circle()
    c.draw()
    c.fill()
}
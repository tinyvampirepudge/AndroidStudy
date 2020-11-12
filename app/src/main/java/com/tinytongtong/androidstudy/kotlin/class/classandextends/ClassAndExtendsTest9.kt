package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：覆盖规则
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 5:37 PM
 * @Version
 */

/**
 * 在 Kotlin 中，实现继承由下述规则规定：如果一个类从它的直接超类继承相同成员的多个实现，
 * 它必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）。
 * 为了表示采用从哪个超类型继承的实现，我们使用由尖括号中超类型名限定的 super，如 super<Base>：
 */
open class Rectangle3 {
    open fun draw() {
        println("Rectangle3#draw")
    }
}

interface Polygon3 {
    // 接口成员默认就是 open 的
    fun draw() {
        println("Polygon3#draw")
    }
}

/**
 * 可以同时继承 Rectangle 与 Polygon，
 * 但是二者都有各自的 draw() 实现，所以我们必须在 Square 中覆盖 draw()， 并提供其自身的实现以消除歧义。
 */
class Square3 : Rectangle3(), Polygon3 {
    override fun draw() {
        super<Rectangle3>.draw()
        super<Polygon3>.draw()
        println("Square3#draw")
    }
}

fun main(args: Array<String>) {
    val s = Square3()
    s.draw()
}


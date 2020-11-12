package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：调用超类实现
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 5:37 PM
 * @Version
 */

/**
 * 派生类中的代码可以使用 super 关键字调用其超类的函数与属性访问器的实现：
 */
open class Rectangle2 {
    open fun draw() {
        println("Rectangle2#draw")
    }

    val borderColor: String
        get() = "black"
}

class FilledRectangle : Rectangle2() {
    override fun draw() {
        super.draw()
        println("FilledRectangle#draw")
    }

    val fillColor: String
        get() = super.borderColor

    /**
     * 在一个内部类中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现：super@Outer
     */
    inner class Filter {
        fun fill() {
            println("FilledRectangle.Filter#fill")
        }

        fun drawAndFill() {
            super@FilledRectangle.draw() // 调用Rectangle的draw()实现
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}")
        }
    }
}

fun main(args: Array<String>) {
    val fr = FilledRectangle()
    val filter = fr.Filter()
    filter.fill()
    filter.drawAndFill()
}



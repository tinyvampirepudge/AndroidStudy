package com.tinytongtong.androidstudy.kotlin.`object`

/**
 * @Description: 对象表达式
 * 有时候，我们需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类。
 * Kotlin 用对象表达式和对象声明处理这种情况。
 *
 * @Author tinytongtong
 * @Date 2020/11/15 9:37 AM
 * @Version
 */

/**
 * 如果超类型有一个构造函数，则必须传递适当的构造函数参数给它。
 * 多个超类型可以由跟在冒号后面的逗号分隔的列表指定：
 */
open class A(x: Int) {
    public open val y: Int = x
}

interface B {}

val ab: A = object : A(1), B {
    override val y: Int = 15
}

/**
 * 任何时候，如果我们只需要“一个对象而已”，并不需要特殊超类型，那么我们可以简单地写：
 */
fun foo() {
    val adHoc = object {
        var x: Int = 10086
        var y: Int = 123
    }
    println(adHoc.x + adHoc.y)
}

/**
 * 请注意，匿名对象可以用作只在本地和私有作用域中声明的类型。
 * 如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型，
 * 如果你没有声明任何超类型，就会是 Any。在匿名对象中添加的成员将无法访问。
 */
class C {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x
//        val x2 = publicFoo().x // 错误；未能解析的引用"x"
    }
}

fun main(args: Array<String>) {
    foo()
}
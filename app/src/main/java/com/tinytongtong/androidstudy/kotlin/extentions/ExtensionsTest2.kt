package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展——扩展是静态解析的
 * https://www.kotlincn.net/docs/reference/extensions.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 11:13 PM
 * @Version
 */

/**
 * 扩展函数是静态分发的，即他们不是根据接收者类型的虚方法。
 * 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的，而不是由表达式运行时求值结果决定的。
 */
fun main(args: Array<String>) {
    open class Shape

    class Rectangle : Shape()

    fun Shape.getName() = "Shape"

    fun Rectangle.getName() = "Rectangle"

    fun printClassName(s: Shape) {
        println(s.getName())
    }

    printClassName(Rectangle()) // Shape

    /**
     * 如果一个类定义有一个成员函数与一个扩展函数，而这两个函数又有相同的接收者类型、 相同的名字，
     * 并且都适用给定的参数，这种情况总是取成员函数。
     */
    class Example {
        fun printFunctionType() {
            println("Class method")
        }
    }

    fun Example.printFunctionType() {
        println("Extension method")
    }

    Example().printFunctionType() // Class method

    fun Example.printFunctionType(i: Int) {
        println("Extension method with params")
    }

    Example().printFunctionType(1) // Extension method with params
}

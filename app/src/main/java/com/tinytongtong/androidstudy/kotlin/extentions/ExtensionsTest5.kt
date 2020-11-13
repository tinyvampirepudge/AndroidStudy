package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展——伴生对象的扩展
 * https://www.kotlincn.net/docs/reference/extensions.html
 *
 * @Author tinytongtong
 * @Date 2020/11/13 9:28 AM
 * @Version
 */

/**
 * 如果一个类定义有一个伴生对象 ，你也可以为伴生对象定义扩展函数与属性。
 * 就像伴生对象的常规成员一样， 可以只使用类名作为限定符来调用伴生对象的扩展成员：
 */

class MyClass {
    companion object {

    }
}

fun MyClass.Companion.printCompanion() {
    println("Companion")
}

val MyClass.Companion.number: Int
    get() = 100

fun main(args: Array<String>) {
    MyClass.Companion.printCompanion()
    println(MyClass.Companion.number)
}

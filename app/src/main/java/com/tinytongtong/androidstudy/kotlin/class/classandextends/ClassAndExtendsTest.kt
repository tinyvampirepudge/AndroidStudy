package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：类
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:41 PM
 * @Version
 */

class Invoice {
    fun foo() {

    }
}

/**
 * 类声明由类名、类头（指定其类型参数、主构造函数等）以及由花括号包围的类体构成。类头与类体都是可选的；
 * 如果一个类没有类体，可以省略花括号。
 */
class Empty

fun main(args: Array<String>) {
    val i = Invoice()

    val e = Empty()
}
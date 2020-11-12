package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

/**
 * @Description: 类与继承：构造函数
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:41 PM
 * @Version
 */

/**
 * 在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数。
 * 主构造函数是类头的一部分：它跟在类名（与可选的类型参数）后。
 */
class Person constructor(firstName: String) {

}

/**
 * 如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
 */
class Person1(firstName: String) {

}

package com.tinytongtong.androidstudy.kotlin.dataclass

/**
 * @Description: 数据类——在类体中声明的属性
 *
 * @Author tinytongtong
 * @Date 2020/11/14 9:49 AM
 * @Version
 */

/**
 * 对于那些自动生成的函数，编译器只使用在主构造函数内部定义的属性。
 * 如需在生成的实现中排除一个属性，请将其声明在类体中：
 */
data class Person2(val name: String) {
    var age: Int = 0
}

fun main(args: Array<String>) {
    val p1 = Person2("John")
    p1.age = 10
    val p2 = Person2("John")
    p2.age = 20
    println(p1 == p2)
}



package com.tinytongtong.androidstudy.kotlin.container

/**
 * @Description: 容器类——List
 * https://www.kotlincn.net/docs/reference/collections-overview.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 7:46 AM
 * @Version
 */
fun main(args: Array<String>) {
    println("——————List测试——————")
    val numbers = listOf("one", "two", "three", "four")
    println(numbers)

    println("——————MutableList测试——————")
    val numbersMutable = mutableListOf("one", "two", "three", "four")
    numbersMutable.add("five")   // 这是可以的
    println(numbersMutable)

    println("——————默认的ArrayList测试——————")
    val al = ArrayList<String>()
    for (x in 0..5) {
        al.add("value:$x")
    }
    println(al)
}
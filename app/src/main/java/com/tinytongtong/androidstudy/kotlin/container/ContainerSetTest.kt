package com.tinytongtong.androidstudy.kotlin.container

/**
 * @Description: 容器类-Set
 * https://www.kotlincn.net/docs/reference/collections-overview.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 9:38 AM
 * @Version
 */

fun main(args: Array<String>) {
    println("——————Set测试——————")
    val numbers = setOf(1, 2, 3, 4)
    println(numbers)
    if (numbers.contains(1)) {
        println("1 is in the set")
    }

    // 当两个 set 具有相同的大小并且对于一个 set 中的每个元素都能在另一个 set 中存在相同元素，则两个 set 相等。
    val numbersBackwards = setOf(4, 3, 2, 1)
    println("The sets are equals: ${numbers == numbersBackwards}")

    println("——————MutableSet测试——————")
    val mutableNumbers = mutableSetOf(1, 2, 3, 4)
    println(mutableNumbers)
    mutableNumbers.add(0)
    mutableNumbers.add(-1)
    println(mutableNumbers)

    val hs = hashSetOf(1, 2, 3, 4, 5)
    println(hs)

    println("——————LinkedHashSet测试——————")
    val lhs = LinkedHashSet<Int>()
    lhs.add(1)
    lhs.add(2)
    lhs.add(3)
    lhs.add(4)
    println(lhs)
}
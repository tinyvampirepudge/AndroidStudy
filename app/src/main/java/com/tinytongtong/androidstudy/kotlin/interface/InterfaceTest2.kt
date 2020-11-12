package com.tinytongtong.androidstudy.kotlin.`interface`

/**
 * @Description: 接口继承
 * https://www.kotlincn.net/docs/reference/interfaces.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 2:19 PM
 * @Version
 */

interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String
    override val name: String
        get() = "$firstName $lastName"
}

data class Employee(
    // 不必实现name
    override val firstName: String,
    override val lastName: String,
    val position: Int
) : Person
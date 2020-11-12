package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展函数
 *
 * @Author tinytongtong
 * @Date 2020/11/11 4:25 PM
 * @Version
 */
class ExtensionTest {
    fun convert(content: String?): Int? {
        println("ExtensionTest#convert content：" + content)
        return content?.toIntOrNull()
    }
}

fun main(args: Array<String>) {
    val e = ExtensionTest()
    println(e.convert("100"))
}
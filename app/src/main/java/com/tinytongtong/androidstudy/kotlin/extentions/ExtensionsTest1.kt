package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展函数、扩展属性。
 *
 * @Author tinytongtong
 * @Date 2020/11/11 4:32 PM
 * @Version
 */

/**
 * 扩展ExtensionTest类的方法
 */
fun ExtensionTest.extension(str: String?) {
    println("ExtensionTest#extension str:" + str)
}

/**
 * 扩展ExtensionTest类的方法，扩展跟现有方法签名相同的方法
 */
fun ExtensionTest.convert(content: String?): Int? {
    println("extension ExtensionTest#convert content:" + content)
    return content?.toIntOrNull()
}

/**
 * 扩展ExtensionTest类的属性
 */
val ExtensionTest.name: String?
    get() = "阿斯顿发"

var ExtensionTest.desc: String?
    get() {
        return "13123"
    }
    set(value) {

    }

fun main(args: Array<String>) {
    val e = ExtensionTest()
//    e.extension("猫了个咪")
//    println(e.name)
//    println(e.desc)
//    e.desc = "阿西吧"
//    println(e.desc)

    // 调用的还是原有的方法，扩展方法不起作用。
    println(e.convert("10086"))
}
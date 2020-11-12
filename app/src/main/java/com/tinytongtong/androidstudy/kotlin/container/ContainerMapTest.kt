package com.tinytongtong.androidstudy.kotlin.container

/**
 * @Description: 容器类——Map
 *
 * @Author tinytongtong
 * @Date 2020/11/12 10:13 AM
 * @Version
 */

fun main(args: Array<String>) {
    println("——————Map测试——————")
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 4)
    print(numbersMap)
    if ("key2" in numbersMap) {
        println("Value by key \"key2\": ${numbersMap["key2"]}")
    }
    if (1 in numbersMap.values) {
        println("The value 1 is in the map")
    }
    if (numbersMap.containsValue(1)) {
        println("The value 1 is in the map")
    }
    println(numbersMap.keys)
    println(numbersMap.values)

    println("——————MutableMap测试——————")
    val mutableNumberMap = mutableMapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 4)
    mutableNumberMap.put("abc", 10086)
    println(mutableNumberMap)

    println("——————LinkedHashMap测试——————")
    val lhm = LinkedHashMap<String, Int>()
    lhm.put("key1", 1)
    lhm.put("key2", 2)
    lhm.put("key3", 3)
    lhm.put("key4", 4)
    println(lhm)
}
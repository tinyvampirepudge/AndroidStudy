package com.tinytongtong.androidstudy.kotlin.`object`

/**
 * @Description: 对象声明：单例模式
 *
 * @Author tinytongtong
 * @Date 2020/11/15 9:59 AM
 * @Version
 */

class DataProvider

object DataProviderManager {
    val list = mutableListOf<DataProvider>()
    fun registerDataProvider(provider: DataProvider) {
        list.add(provider)
    }

    val allDataProvider: Collection<DataProvider>
        get() = list
}

class MyClass {
    object ObjectClass {
        fun foo() {
            println("MyClass#ObjectClass#foo")
        }
    }
}

class MyClass1 {
    companion object Factory {
        fun create(): MyClass1 = MyClass1()
    }
}

class MyClass2 {
    companion object {
        fun create(): MyClass2 = MyClass2()
    }
}

/**
 * 请注意，即使伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然是真实对象的实例成员，
 * 而且，例如还可以实现接口：
 */

interface Factory<T> {
    fun create(): T
}

class MyClass3 {
    companion object : Factory<MyClass3> {
        override fun create(): MyClass3 = MyClass3()
    }
}

fun main(args: Array<String>) {
    DataProviderManager.registerDataProvider(DataProvider())
    println(DataProviderManager.allDataProvider)

    MyClass.ObjectClass.foo()


    val instance = MyClass1.create()

    val instance2 = MyClass2.Companion.create()
    val instance3 = MyClass2.create()

    val f: Factory<MyClass3> = MyClass3
    val m3 = f.create()
}

package com.tinytongtong.androidstudy.kotlin.extentions

/**
 * @Description: 扩展——扩展声明为成员
 * https://www.kotlincn.net/docs/reference/extensions.html
 *
 * @Author tinytongtong
 * @Date 2020/11/13 10:24 AM
 * @Version
 */

class Host(val hostname: String) {
    fun printHostname() {
        print(hostname)
    }
}

class Connection(val host: Host, val port: Int) {
    fun printPort() {
        print(port)
    }

    fun Host.printConnectionString() {
        printHostname() // 调用Host.printHostname
        print(":")
        printPort() // 调用 Connection.printPort
    }

    fun connect() {
        host.printConnectionString() // 调用扩展函数
    }

    /**
     * 对于分发接收者与扩展接收者的成员名字冲突的情况，扩展接收者优先。要引用分发接收者的成员你可以使用 限定的 this 语法。
     */
    fun Host.getConnectionString() {
        toString() // 调用Host.toString
        this@Connection.toString() // 调用Connection.toString
    }
}

open class Base1 {

}

class Derived1 : Base1() {

}

open class BaseCaller {
    open fun Base1.printFunctionInfo() {
        println("Base extension function in BaseCaller")
    }

    open fun Derived1.printFunctionInfo() {
        println("Derived extension function in BaseCaller")
    }

    fun call(b: Base1) {
        b.printFunctionInfo() // 调用扩展函数
    }
}

class DerivedCaller : BaseCaller() {
    override fun Base1.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }

    override fun Derived1.printFunctionInfo() {
        println("Derived extension function in DerivedCaller")
    }
}

fun main(args: Array<String>) {
//    Connection(Host("kotl.in"), 443).connect()

    /**
     * 声明为成员的扩展可以声明为 open 并在子类中覆盖。
     * 这意味着这些函数的分发对于分发接收者类型是虚拟的，但对于扩展接收者类型是静态的。
     */
    BaseCaller().call(Base1()) // Base extension function in BaseCaller
    DerivedCaller().call(Base1()) // Base extension function in DerivedCaller, 分发接收者虚拟解析
    DerivedCaller().call(Derived1()) // Base extension function in DerivedCaller，扩展接收者静态解析
}

package com.tinytongtong.androidstudy.kotlin.`class`.classandextends

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @Description: 类与继承：继承
 * https://www.kotlincn.net/docs/reference/classes.html
 *
 * @Author tinytongtong
 * @Date 2020/11/12 4:26 PM
 * @Version
 */

open class Base(p: Int)

class Derived(p: Int) : Base(p)

class MyView : View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}
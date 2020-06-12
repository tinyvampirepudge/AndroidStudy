package com.tinytongtong.androidstudy

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.didichuxing.doraemonkit.DoraemonKit

/**
 * @Description:
 *
 * @Author wangjianzhou@qding.me
 * @Date 2020/6/11 5:18 PM
 * @Version
 */
class MyApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        DoraemonKit.install(this, "c7e58cce7b259d5c8b8bdeec039b9e34")
    }
}
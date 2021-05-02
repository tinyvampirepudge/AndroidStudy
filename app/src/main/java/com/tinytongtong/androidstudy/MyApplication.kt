package com.tinytongtong.androidstudy

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.multidex.MultiDex
import com.didichuxing.doraemonkit.DoraemonKit
import com.squareup.leakcanary.LeakCanary
import com.tencent.mmkv.MMKV
import com.tinytongtong.androidstudy.componentcallbacks.ComponentCallbacksActivity

/**
 * @Description:
 *
 * @Author tinytongtong
 * @Date 2020/6/11 5:18 PM
 * @Version
 */
class MyApplication : Application() {
    companion object {
        var instance: Application? = null
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        LeakCanary.install(this)

        DoraemonKit.install(this, "c7e58cce7b259d5c8b8bdeec039b9e34")

        // mmkv
        val rootDir = MMKV.initialize(this)
        println("mmkv rootDir：" + rootDir)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.e("MyApplication", String.format("onTrimMemory level:%s", level))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(
            "MyApplication",
            String.format("onConfigurationChanged newConfig:%s", newConfig)
        )
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.e("MyApplication", String.format("onLowMemory"))
    }
}
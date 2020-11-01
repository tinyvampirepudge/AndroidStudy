package com.tinytongtong.androidstudy.activity.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.tinytongtong.androidstudy.R
import com.tinytongtong.tinyutils.LogUtils

/**
 * @Description: 屏幕旋转导致生命周期变化
 *
 * @Author tinytongtong
 * @Date 2020/10/31 11:53 PM
 * @Version
 */
class ConfigChangedActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ConfigChangedActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e(TAG, "onCreate(savedInstanceState)")
        setContentView(R.layout.activity_config_changed)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LogUtils.e(TAG, "onCreate(savedInstanceState, persistentState)")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        LogUtils.e(TAG, "onPostCreate")
        super.onPostCreate(savedInstanceState)
    }

    override fun onStart() {
        LogUtils.e(TAG, "onStart")
        super.onStart()
    }


    override fun onRestart() {
        LogUtils.e(TAG, "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        LogUtils.e(TAG, "onResume")
        super.onResume()
    }

    override fun onPostResume() {
        LogUtils.e(TAG, "onPostResume")
        super.onPostResume()
    }

    override fun onPause() {
        LogUtils.e(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        LogUtils.e(TAG, "onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        LogUtils.e(TAG, "onSaveInstanceState(outState, outPersistentState)")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.e(TAG, "onSaveInstanceState(outState)")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtils.e(TAG, "onRestoreInstanceState(savedInstanceState)")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        LogUtils.e(TAG, "onRestoreInstanceState(savedInstanceState, persistentState)")
    }

    override fun onDestroy() {
        LogUtils.e(TAG, "onDestroy")
        super.onDestroy()
    }
}
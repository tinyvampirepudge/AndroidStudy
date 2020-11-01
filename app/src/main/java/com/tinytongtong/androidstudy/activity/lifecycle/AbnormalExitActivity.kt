package com.tinytongtong.androidstudy.activity.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import com.tinytongtong.androidstudy.R
import com.tinytongtong.tinyutils.LogUtils

/**
 * @Description: Activity异常退出的生命周期
 *
 * @Author tinytongtong
 * @Date 2020/10/31 11:54 PM
 * @Version
 */
class AbnormalExitActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AbnormalExitActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e(TAG, "onCreate(savedInstanceState)")
        setContentView(R.layout.activity_abnormal_exit)

        Handler().postDelayed(Runnable {
//            val i = 100/0
        },5000)
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
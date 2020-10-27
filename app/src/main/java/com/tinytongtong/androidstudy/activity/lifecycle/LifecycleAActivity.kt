package com.tinytongtong.androidstudy.activity.lifecycle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import com.tinytongtong.tinyutils.LogUtils
import kotlinx.android.synthetic.main.activity_lifecycle_a.*

class LifecycleAActivity : AppCompatActivity() {
    companion object{
        const val TAG = "LifecycleAActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtils.e(TAG,"onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_a)

        btnStartB.setOnClickListener {
            startActivity(Intent(this, LifecycleBActivity::class.java))
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        LogUtils.e(TAG,"onPostCreate")
        super.onPostCreate(savedInstanceState)
    }

    override fun onStart() {
        LogUtils.e(TAG,"onStart")
        super.onStart()
    }


    override fun onRestart() {
        LogUtils.e(TAG,"onRestart")
        super.onRestart()
    }

    override fun onResume() {
        LogUtils.e(TAG,"onResume")
        super.onResume()
    }

    override fun onPostResume() {
        LogUtils.e(TAG,"onPostResume")
        super.onPostResume()
    }

    override fun onPause() {
        LogUtils.e(TAG,"onPause")
        super.onPause()
    }

    override fun onStop() {
        LogUtils.e(TAG,"onStop")
        super.onStop()
    }

    override fun onDestroy() {
        LogUtils.e(TAG,"onDestroy")
        super.onDestroy()
    }
}
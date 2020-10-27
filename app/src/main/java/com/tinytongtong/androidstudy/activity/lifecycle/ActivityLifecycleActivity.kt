package com.tinytongtong.androidstudy.activity.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_lifecycle.*

/**
 * @Description: Activity生命周期
 *
 * @Author tinytongtong
 * @Date 2020/10/27 11:24 AM
 * @Version
 */
class ActivityLifecycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        btnAStartB.setOnClickListener {
            startActivity(Intent(this, LifecycleAActivity::class.java))
        }
    }
}
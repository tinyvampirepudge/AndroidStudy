package com.tinytongtong.androidstudy.anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tinytongtong.androidstudy.R

class ActivityAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)
    }

    override fun finish() {
        super.finish()
        // 退出，从上往下动画
        overridePendingTransition(
            R.anim.slide_in_up,
            R.anim.slide_out_up
        )
    }
}
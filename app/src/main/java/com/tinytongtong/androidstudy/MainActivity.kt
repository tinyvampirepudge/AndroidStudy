package com.tinytongtong.androidstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.bitmap.BitmapEntryActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_bitmap.setOnClickListener {
            startActivity(Intent(this, BitmapEntryActivity::class.java))
        }

        btn_activity_anim.setOnClickListener {
            startActivity(Intent(this, ActivityAnimActivity::class.java))
            // 进入，从下往上动画
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
        }
    }
}
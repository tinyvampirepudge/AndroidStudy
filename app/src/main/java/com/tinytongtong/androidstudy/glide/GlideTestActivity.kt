package com.tinytongtong.androidstudy.glide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_glide_test.*

/**
 * @Description: glide源码：3.7.0
 *
 * @Author tinytongtong
 * @Date 2020/11/8 5:56 PM
 * @Version
 */
class GlideTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_test)

        btnLoad.setOnClickListener {
            val url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"
            Glide
                .with(this)
                .load(url)
                .into(iv)
        }

        btnGlideV4.setOnClickListener{
            startActivity(Intent(this, GlideV4Activity::class.java))
        }
    }
}
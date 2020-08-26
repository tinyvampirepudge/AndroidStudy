package com.tinytongtong.androidstudy.draw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R

/**
 * @Description: 一个acticity中有一个自定义的ViewGroup 重写这个ViewGroup的onDraw()方法 在里面打印一条数据 activity 启动后 这个log会打印吗
 * 不会
 *
 * @Author tinytongtong
 * @Date 2020/8/26 4:29 PM
 * @Version
 */
class CustomViewGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_group)


    }
}
package com.tinytongtong.androidstudy.drawflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tinytongtong.androidstudy.R

/**
 * @Description: 直接继承View的自定义控件需要重写onMeasure方法并设置wrap_content时的自身大小，
 * 否则在布局中使用wrap_content就相当于使用match_parent。
 *
 * @Author tinytongtong
 * @Date 2020/10/26 9:27 AM
 * @Version
 */
class ChildViewWrapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_view_wrap)
    }
}
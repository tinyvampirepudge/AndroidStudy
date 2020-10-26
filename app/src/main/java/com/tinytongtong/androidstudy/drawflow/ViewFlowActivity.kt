package com.tinytongtong.androidstudy.drawflow

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_view_flow.*

/**
 * @Description: View绘制相关
 *
 * @Author tinytongtong
 * @Date 2020/10/26 9:14 AM
 * @Version
 */
class ViewFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flow)

        btn1.setOnClickListener {
            startActivity(Intent(this, ChildViewWrapActivity::class.java))
        }
    }
}
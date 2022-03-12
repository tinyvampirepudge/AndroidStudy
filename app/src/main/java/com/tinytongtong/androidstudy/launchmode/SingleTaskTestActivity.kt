package com.tinytongtong.androidstudy.launchmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_single_task_test.*

/**
 * @Description: SingleTask测试
 *
 * @Author wangjianzhou
 * @Date 2021/12/23 3:45 PM
 */
class SingleTaskTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SingleTaskTest", "onCreate")
        setContentView(R.layout.activity_single_task_test)

        btn_single_task.setOnClickListener {
            startActivity(Intent(this, SingleTaskTest2Activity::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("SingleTaskTest", "onNewIntent")
    }
}
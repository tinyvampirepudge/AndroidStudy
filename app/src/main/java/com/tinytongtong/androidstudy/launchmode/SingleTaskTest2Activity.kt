package com.tinytongtong.androidstudy.launchmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_single_task_test2.*

/**
 * @Description: SingleTask相关校验
 *
 * @Author wangjianzhou
 * @Date 2021/12/23 3:47 PM
 */
class SingleTaskTest2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SingleTask2Test", "onCreate")
        setContentView(R.layout.activity_single_task_test2)

        /**
         * 所有系统singleTask都可正常work，即SubAct被销毁，露出之前的MainAct（使其位于栈顶），并且其onNewIntent方法被回调
         */
        btn_single_task_start_activity.setOnClickListener {
            startActivity(Intent(this, SingleTaskTestActivity::class.java))
        }

        /**
         * <11系统，singleTask的行为失效，和standard一样的行为（总是创建新的实例），
         * 这个反而导致上面的收银台case在这些设备上没问题,
         * >=11，singleTask行为正常（Google偷偷修正了这个行为，同startActivity拉齐了下）
         */
        btn_single_task_start_activity_for_result.setOnClickListener {
            startActivityForResult(Intent(this, SingleTaskTestActivity::class.java), 100)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("SingleTaskTest2", "onNewIntent")
    }
}
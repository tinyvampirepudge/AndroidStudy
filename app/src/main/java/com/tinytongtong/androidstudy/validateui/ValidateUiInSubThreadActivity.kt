package com.tinytongtong.androidstudy.validateui

import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_validate_ui_in_sub_thread.*


/**
 * @Description: 子线程更新UI
 *
 * @Author wangjianzhou
 * @Date 2020/6/17 8:37 PM
 * @Version TODO
 */
class ValidateUiInSubThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_ui_in_sub_thread)

        val tv = TextView(this)
        tv.text = "猫了个咪"
        Log.e("ValidateUiInSubThread","猫了个咪")
        btn_validate_ui_in_sub_thread.post {
            Log.e("ValidateUiInSubThread","tv : ${tv.measuredHeight}, ${tv.measuredHeight}")
        }

        btn_validate_ui_in_sub_thread.setOnClickListener {
            Thread {
                Looper.prepare()
                val tv = TextView(this)
                tv.text = "猫了个咪"

                val LAYOUT_FLAG: Int
                LAYOUT_FLAG = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                } else {
                    WindowManager.LayoutParams.TYPE_PHONE
                }

                val params = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    LAYOUT_FLAG,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
                )
                windowManager.addView(tv, params)
                Looper.loop()
            }.start()
        }
    }
}
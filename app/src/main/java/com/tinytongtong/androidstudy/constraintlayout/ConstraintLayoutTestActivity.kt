package com.tinytongtong.androidstudy.constraintlayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.utils.LogUtil
import com.tinytongtong.tinyutils.LogUtils
import com.tinytongtong.tinyutils.ScreenUtils
import kotlinx.android.synthetic.main.activity_constraint_layout_test.*
import kotlin.math.log

/**
 * @Description: ConstraintLayout相关测试
 *
 * @Author tinytongtong
 * @Date 2021/12/23 11:01 AM
 * @Version
 */
class ConstraintLayoutTestActivity : AppCompatActivity() {
    companion object {
        private val TAG = "ConstraintLayout"
    }

    var mContext: Context? = null

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_constraint_layout_test)

        root.post {
            Log.e(TAG, String.format("measuredHeight:%s", root.measuredHeight))
        }

        bottom_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var height = index%6 * 100 + 50f
                var mlp = mid_tv.layoutParams
                mlp.height= ScreenUtils.dip2px(mContext as ConstraintLayoutTestActivity, height)
                mid_tv.layoutParams = mlp
                index++
            }
        })
    }
}
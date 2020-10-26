package com.tinytongtong.androidstudy.drawflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_multiple_on_measure.*

/**
 * @Description: onMeasure、onLayout为何会多次调用
 * 默认调用两遍onMeasure、onLayout、draw
 *
 * @Author tinytongtong
 * @Date 2020/10/26 4:48 PM
 * @Version
 */
class MultipleOnMeasureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_on_measure)

        view.setOnClickListener {
            view.invalidate()
        }
    }
}
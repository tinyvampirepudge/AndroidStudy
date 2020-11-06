package com.tinytongtong.androidstudy.sharedpreferences

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R

/**
 * @Description: SharedPreferences原理解析
 * https://www.jianshu.com/p/9ae0f6842689
 *
 * @Author tinytongtong
 * @Date 2020/11/6 5:15 PM
 * @Version
 */
class SharedPreferenceTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference_test)

        val sp = getSharedPreferences("SharedPreferenceTestActivity", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("asdfasdf", "asdfasdf")
        editor.apply()
    }
}
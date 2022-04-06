package com.tinytongtong.androidstudy.jetpack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    companion object {
        private val TAG = DataBindingActivity::class.simpleName
        fun actionStart(context: Activity) {
            val starter = Intent(context, DataBindingActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)

        var dataBinding: ActivityDataBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        dataBinding.lifecycleOwner = this
        var user = User()
        user.name.set("猫了个咪")
        user.age.set(18)
        dataBinding.user = user

        dataBinding.btnUpdateData.setOnClickListener {
            user.name.set("王蛋蛋的爸比")
            user.age.set(36)
        }

        dataBinding.btnUpdateText.setOnClickListener {
            dataBinding.tvAge.text = "哈哈哈"
            Log.e(TAG, "user.name: ${user.name}")
        }
    }
}
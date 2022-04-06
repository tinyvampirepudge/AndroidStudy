package com.tinytongtong.androidstudy.jetpack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.databinding.ActivityJetpackBinding

class JetPackActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Activity) {
            val starter = Intent(context, JetPackActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var viewBinding: ActivityJetpackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewBinding
        viewBinding = ActivityJetpackBinding.inflate(layoutInflater)
        var view = viewBinding.root
        setContentView(view)

        viewBinding.btnLivedata.setOnClickListener {
            LiveDataActivity.actionStart(this)
        }
        viewBinding.btnDataBinding.setOnClickListener {
            DataBindingActivity.actionStart(this)
        }
    }
}
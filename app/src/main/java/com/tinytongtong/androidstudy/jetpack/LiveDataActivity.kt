package com.tinytongtong.androidstudy.jetpack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.tinytongtong.androidstudy.databinding.ActivityLiveDataBinding
import kotlinx.coroutines.*

class LiveDataActivity : AppCompatActivity() {

    val liveData: MutableLiveData<String> = MutableLiveData()

    companion object {
        private val TAG = LiveDataActivity::class.simpleName
        fun actionStart(context: Activity) {
            val starter = Intent(context, LiveDataActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var viewBinding: ActivityLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        liveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e(TAG, "onChanged t:$t")
            }
        })

        viewBinding.btnPostValue.setOnClickListener {
            // 只会收到b
            liveData.postValue("a")
            liveData.postValue("b")
        }
        viewBinding.btnSetValue.setOnClickListener {
            // a1、b1均会收到
            liveData.setValue("a1")
            liveData.setValue("b1")
        }

        viewBinding.btnSetPostValue.setOnClickListener {
            // a2、b2均会收到
            liveData.setValue("a2")
            liveData.postValue("b2")
        }

        viewBinding.btnPostSetValue.setOnClickListener {
            // b3、a3均会收到。 b3先收到
            liveData.postValue("a3")
            liveData.setValue("b3")
        }

        // postValue在不可见时设置的值，可见时不会触发。
        viewBinding.btnSetValueInvisible.setOnClickListener {
            // 再次可见时，会收到c4
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    Log.e(TAG, "runBlocking withContext 111")
                    delay(5000)
                    Log.e(TAG, "runBlocking withContext 111 after delay")
                }
                liveData.setValue("a4")
                liveData.setValue("b4")
                liveData.setValue("c4")
            }
            Log.e(TAG, "runBlocking 111")
        }

        viewBinding.btnPostValueInvisible.setOnClickListener {
            // 再次可见时，会收到c5
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "runBlocking withContext 222 ")
                    delay(5000)
                    Log.e(TAG, "runBlocking withContext 222 after delay")
                    liveData.postValue("a5")
                    liveData.postValue("b5")
                    liveData.postValue("c5")
                }
            }
            Log.e(TAG, "runBlocking 222")
        }
    }
}
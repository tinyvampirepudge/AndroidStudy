package com.tinytongtong.androidstudy.handler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_handler_test.*
import java.lang.reflect.Field

/**
 * @Description:
 *
 * @Author tinytongtong
 * @Date 2020/11/5 10:04 PM
 * @Version
 */
class HandlerTestActivity : AppCompatActivity() {
    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> Toast.makeText(this@HandlerTestActivity, "接受到了消息", Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(this@HandlerTestActivity, "else", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_test)

        btnSendMsg.setOnClickListener {
            val msg = Message.obtain()
            val field: Field = Message::class.java.getDeclaredField("callback")
            field.setAccessible(true);
            field.set(msg, object : Runnable {
                override fun run() {
                    Toast.makeText(this@HandlerTestActivity, "猫了个咪", Toast.LENGTH_SHORT).show()
                }
            })
            handler.sendMessage(msg)
        }
    }
}
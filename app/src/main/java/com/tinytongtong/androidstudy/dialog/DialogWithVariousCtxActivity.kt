package com.tinytongtong.androidstudy.dialog

import android.content.*
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.tinytongtong.androidstudy.MyApplication
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_dialog_with_application_ctx.*

/**
 * @Description:
 *
 * @Author tinytongtong
 * @Date 2020/9/24 5:27 PM
 * @Version
 */
class DialogWithVariousCtxActivity : AppCompatActivity() {
    companion object{
        const val TAG = "DialogWithVarious"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"onCreate")
        setContentView(R.layout.activity_dialog_with_application_ctx)
        btnDialogActivityCtx.setOnClickListener {
            val dialog = AlertDialog.Builder(DialogWithVariousCtxActivity@ this)
                .setTitle("Activity中Context启动的Dialog")
                .setMessage("Activity中Context启动的Dialog")
                .setCancelable(true)
                .setNegativeButton("cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                .setPositiveButton("ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                .show()
        }

        // java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.
        btnDialogAppCtx.setOnClickListener {
            val dialog = MyApplication.instance?.let { it1 ->
                AlertDialog.Builder(it1)
                    .setTitle("Application中Context启动的Dialog")
                    .setMessage("Application中Context启动的Dialog")
                    .setCancelable(true)
                    .setNegativeButton("cancel", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                    .setPositiveButton("ok", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                    .show()
            }
        }

        // java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.
        btnDialogServiceCtx.setOnClickListener {
            val i = Intent(DialogWithVariousCtxActivity@ this, DialogService::class.java)
            startService(i)
        }

        // 动态广播，上下文是发送者的上下文
        val dynamicReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.e("BroadcastReceiver", "Dynamic onReceive")
                val dialog = context?.let {
                    AlertDialog.Builder(it)
                        .setTitle("BroadcastReceiver中Context启动的Dialog")
                        .setMessage("Dynamic BroadcastReceiver中Context启动的Dialog")
                        .setCancelable(true)
                        .setNegativeButton("cancel", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog?.dismiss()
                            }
                        })
                        .setPositiveButton("ok", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog?.dismiss()
                            }
                        })
                        .show()
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction("btnDialogBroadcastReceiverCtx")
        registerReceiver(dynamicReceiver, intentFilter)

        btnDialogBroadcastReceiverCtx.setOnClickListener {
            val i = Intent()
            i.setAction("btnDialogBroadcastReceiverCtx")
            sendBroadcast(i)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"onPause")
    }

}
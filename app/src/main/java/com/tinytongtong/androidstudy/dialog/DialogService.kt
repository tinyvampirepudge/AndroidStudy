package com.tinytongtong.androidstudy.dialog

import android.app.Service
import android.content.DialogInterface
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AlertDialog

class DialogService : Service() {

    override fun onCreate() {
        Log.e("DialogService","onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("DialogService","onStartCommand")
        // Caused by: java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.
        val dialog = AlertDialog.Builder(DialogService@ this)
            .setTitle("Service中Context启动的Dialog")
            .setMessage("Service中Context启动的Dialog")
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
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}

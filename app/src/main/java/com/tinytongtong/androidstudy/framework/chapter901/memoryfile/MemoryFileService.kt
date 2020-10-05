package com.tinytongtong.androidstudy.framework.chapter901.memoryfile

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MemoryFileService : Service() {

    private val binder by lazy { MemoryFileBinder() }

    override fun onBind(intent: Intent): IBinder = binder
}

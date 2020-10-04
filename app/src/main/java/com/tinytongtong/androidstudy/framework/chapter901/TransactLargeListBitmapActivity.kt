package com.tinytongtong.androidstudy.framework.chapter901

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.aidl.ILargeBitmapListCallback

/**
 * @Description: 跨进程传递列表的Bitmap，是可以的
 *
 * @Author tinytongtong
 * @Date 2020/10/4 5:32 PM
 * @Version
 */
class TransactLargeListBitmapActivity : AppCompatActivity() {
    companion object {
        val TAG = TransactLargeListBitmapActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transact_large_list_bitmap)

        val result = intent
        val bundle = result.extras
        val binder = bundle?.getBinder("bitmap-list-binder")
        binder?.let {
            if (binder is ILargeBitmapListCallback) {
                var bmpList = binder.bitmapList
                for (x in 0 until bmpList.size) {
                    Log.e(
                        TAG,
                        "index:${x}, allocationByteCount:${bmpList.get(x).allocationByteCount}"
                    )
                }
            }
        }
    }
}